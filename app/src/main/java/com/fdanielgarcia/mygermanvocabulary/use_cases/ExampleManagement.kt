package com.fdanielgarcia.mygermanvocabulary.use_cases

import com.google.mlkit.genai.common.DownloadStatus
import com.google.mlkit.genai.common.FeatureStatus
import com.google.mlkit.genai.prompt.Generation

class ExampleManagement {

    private val generativeModel = Generation.getClient()

    suspend fun checkAndPrepare(): Result<Unit> {
        return try {
            when (generativeModel.checkStatus()) {
                FeatureStatus.AVAILABLE -> Result.success(Unit)
                FeatureStatus.DOWNLOADING -> Result.failure(Exception("DOWNLOADING"))
                FeatureStatus.DOWNLOADABLE -> {
                    var downloadResult: Result<Unit> = Result.failure(Exception("DOWNLOAD_FAILED"))
                    generativeModel.download().collect { status ->
                        when (status) {
                            DownloadStatus.DownloadCompleted -> {
                                downloadResult = Result.success(Unit)
                            }
                            is DownloadStatus.DownloadFailed -> {
                                downloadResult = Result.failure(status.e)
                            }
                            else -> {}
                        }
                    }
                    downloadResult
                }
                else -> Result.failure(Exception("UNAVAILABLE"))
            }
        } catch (e: Exception) {
            Result.failure(e)
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

        return try {
            val prompt =
                "Write exactly one German sentence using the word \"$word\" ($wordType). " +
                "The sentence must be between $minWords and $maxWords words long. " +
                "Reply with only the sentence."
            val response = generativeModel.generateContent(prompt)
            val text = response.candidates.firstOrNull()?.text?.trim() ?: ""
            Result.success(text)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
