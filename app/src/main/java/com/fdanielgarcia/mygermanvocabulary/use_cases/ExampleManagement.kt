package com.fdanielgarcia.mygermanvocabulary.use_cases

import com.google.mlkit.genai.common.DownloadCallback
import com.google.mlkit.genai.common.DownloadConfig
import com.google.mlkit.genai.inference.GenerativeModel
import com.google.mlkit.genai.inference.GenerativeModelConfig
import com.google.mlkit.genai.inference.GenerativeModelStatus
import com.google.mlkit.genai.inference.InferenceParams
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine

class ExampleManagement {

    private var model: GenerativeModel? = null
    private var prepared = false

    private suspend fun checkAndPrepare(): Result<Unit> = suspendCancellableCoroutine { cont ->
        if (prepared && model != null) {
            cont.resume(Result.success(Unit))
            return@suspendCancellableCoroutine
        }
        try {
            val config = GenerativeModelConfig.builder()
                .build()
            val generativeModel = GenerativeModel.getInstance(config)
            generativeModel.checkStatus()
                .addOnSuccessListener { status ->
                    when (status) {
                        GenerativeModelStatus.AVAILABLE -> {
                            model = generativeModel
                            prepared = true
                            cont.resume(Result.success(Unit))
                        }
                        GenerativeModelStatus.DOWNLOADING, GenerativeModelStatus.DOWNLOADABLE -> {
                            generativeModel.download(
                                DownloadConfig.builder().build(),
                                object : DownloadCallback {
                                    override fun onDownloadCompleted() {
                                        model = generativeModel
                                        prepared = true
                                        if (cont.isActive) cont.resume(Result.success(Unit))
                                    }
                                    override fun onDownloadFailed(e: Exception) {
                                        if (cont.isActive) cont.resume(Result.failure(e))
                                    }
                                    override fun onDownloadStarted(bytesToDownload: Long) {}
                                    override fun onDownloadProgress(
                                        totalBytesDownloaded: Long,
                                        totalBytesToDownload: Long
                                    ) {}
                                }
                            )
                            if (status == GenerativeModelStatus.DOWNLOADING && cont.isActive) {
                                // Resume with a specific "DOWNLOADING" failure so the UI can show the appropriate message
                                cont.resume(Result.failure(Exception("DOWNLOADING")))
                            }
                        }
                        else -> {
                            cont.resume(
                                Result.failure(Exception("UNAVAILABLE"))
                            )
                        }
                    }
                }
                .addOnFailureListener { e ->
                    if (cont.isActive) cont.resume(Result.failure(e))
                }
        } catch (e: Exception) {
            if (cont.isActive) cont.resume(Result.failure(e))
        }
    }

    suspend fun generateExample(
        word: String,
        wordType: String,
        minWords: Int,
        maxWords: Int
    ): Result<String> {
        val prepResult = checkAndPrepare()
        if (prepResult.isFailure) {
            val msg = prepResult.exceptionOrNull()?.message ?: "Unknown error"
            return Result.failure(Exception(msg))
        }

        val currentModel = model
            ?: return Result.failure(Exception("UNAVAILABLE"))

        val prompt =
            "Write exactly one German sentence using the word \"$word\" ($wordType). " +
            "The sentence must be between $minWords and $maxWords words long. " +
            "Reply with only the sentence."

        return suspendCancellableCoroutine { cont ->
            try {
                val params = InferenceParams.builder().build()
                currentModel.generateContent(prompt, params)
                    .addOnSuccessListener { response ->
                        val text = response.text?.trim() ?: ""
                        if (cont.isActive) cont.resume(Result.success(text))
                    }
                    .addOnFailureListener { e ->
                        if (cont.isActive) cont.resume(Result.failure(e))
                    }
            } catch (e: Exception) {
                if (cont.isActive) cont.resume(Result.failure(e))
            }
        }
    }
}
