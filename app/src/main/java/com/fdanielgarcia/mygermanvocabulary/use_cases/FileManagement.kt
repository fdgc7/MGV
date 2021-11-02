package com.fdanielgarcia.mygermanvocabulary.use_cases

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.domain.*
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.InputStreamReader

class FileManagement(val activity: Activity) {
    val vocabularyDB by lazy { (activity.application as MGVApplication).vocabularyDB }

    fun loadFile(loadFileLauncher: ActivityResultLauncher<Intent>) {
        val fileIntent = Intent(Intent.ACTION_GET_CONTENT)
        fileIntent.addCategory(Intent.CATEGORY_OPENABLE)
        fileIntent.type = "text/*"
        loadFileLauncher.launch(fileIntent)
    }

    fun loadCSV(fileType: String, uri: Uri): Int {
        val contentResolver = activity.contentResolver
        val inputStream = contentResolver?.openInputStream(uri)
        val file = InputStreamReader(inputStream, "ISO-8859-1")
        val bufferedReader = BufferedReader(file)
        val csvParser = CSVParser(
            bufferedReader,
            CSVFormat.RFC4180.withFirstRecordAsHeader().withDelimiter(';')
        )
        //Todo: Check Headers and numbers of fields --> https://stackoverflow.com/questions/36269387/get-csv-file-header-using-apache-commons

        vocabularyDB.emptyTable(fileType)

        when (fileType) {
            "MasculineSubstantives" -> {
                for (csvRecord in csvParser) {
                    val masculineSubstantive = Substantive(
                        csvRecord.get("der"),
                        Gender.MASCULINE,
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertSubstantive(masculineSubstantive)
                }
            }
            "FeminineSubstantives" -> {
                for (csvRecord in csvParser) {
                    val feminineSubstantive = Substantive(
                        csvRecord.get("die"),
                        Gender.FEMININE,
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertSubstantive(feminineSubstantive)
                }
            }
            "NeuterSubstantives" -> {
                for (csvRecord in csvParser) {
                    val neuterSubstantive = Substantive(
                        csvRecord.get("das"),
                        Gender.NEUTER,
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertSubstantive(neuterSubstantive)
                }
            }
            "Verbs" -> {
                for (csvRecord in csvParser) {
                    val verb = Verb(
                        csvRecord.get("Infinitiv"),
                        csvRecord.get("Präsens"),
                        csvRecord.get("Präteritum"),
                        csvRecord.get("Perfekt"),
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertVerb(verb)
                }
            }
            "Adjectives" -> {
                for (csvRecord in csvParser) {
                    val adjective = Adjective(
                        csvRecord.get("Adjektiv"),
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertAdjective(adjective)
                }

            }
            "Adverbs" -> {
                for (csvRecord in csvParser) {
                    val adverb = Adverb(
                        csvRecord.get("Adverb"),
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertAdverb(adverb)
                }

            }
            "Conjunctions" -> {
                for (csvRecord in csvParser) {
                    val conjunction = Conjunction(
                        csvRecord.get("Konjunktion"),
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertConjunction(conjunction)
                }

            }
            "Prepositions" -> {
                for (csvRecord in csvParser) {
                    val preposition = Preposition(
                        csvRecord.get("Präposition"),
                        csvRecord.get("Bedeutung")
                    )
                    vocabularyDB.insertPreposition(preposition)
                }

            }
        }

        return vocabularyDB.countElements(fileType)
    }
}