package com.fdanielgarcia.mygermanvocabulary.use_cases

import android.app.Activity
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.VocabularyList
import com.fdanielgarcia.mygermanvocabulary.domain.Gender

class ListManagement(val activity: Activity) {
    val vocabularyDB by lazy { (activity.application as MGVApplication).vocabularyDB }

    fun loadList(vocabularyType: String): VocabularyList {
        lateinit var vocabularyList: VocabularyList

        when (vocabularyType) {
            "AllSubstantives" -> {
                vocabularyList = vocabularyDB.getSubstantives(Gender.MASCULINE)
                vocabularyList.addAll(vocabularyDB.getSubstantives(Gender.FEMININE))
                vocabularyList.addAll(vocabularyDB.getSubstantives(Gender.NEUTER))
            }
            "MasculineSubstantives" -> {
                vocabularyList = vocabularyDB.getSubstantives(Gender.MASCULINE)
            }
            "FeminineSubstantives" -> {
                vocabularyList = vocabularyDB.getSubstantives(Gender.FEMININE)
            }
            "NeuterSubstantives" -> {
                vocabularyList = vocabularyDB.getSubstantives(Gender.NEUTER)
            }
            "Verbs" -> {
                vocabularyList = vocabularyDB.getVerbs()
            }
            "Adjectives" -> {
                vocabularyList = vocabularyDB.getAdjectives()
            }
            "Adverbs" -> {
                vocabularyList = vocabularyDB.getAdverbs()
            }
            "Conjunctions" -> {
                vocabularyList = vocabularyDB.getConjunctions()
            }
            "Prepositions" -> {
                vocabularyList = vocabularyDB.getPrepositions()
            }
        }

        return vocabularyList
    }

    fun showStored(): String {
        val masSubs = vocabularyDB.countElements("MasculineSubstantives")
        val femSubs = vocabularyDB.countElements("FeminineSubstantives")
        val ntrSubs = vocabularyDB.countElements("NeuterSubstantives")
        val verbs = vocabularyDB.countElements("Verbs")
        val adjectives = vocabularyDB.countElements("Adjectives")
        val adverbs = vocabularyDB.countElements("Adverbs")
        val conjunctions = vocabularyDB.countElements("Conjunctions")
        val prepositions = vocabularyDB.countElements("Prepositions")
        val pronouns = vocabularyDB.countElements("Pronouns")
        val total = masSubs + femSubs + ntrSubs + verbs + adjectives + adverbs + conjunctions + prepositions + pronouns

        return  activity.resources?.getString(R.string.masculine_substantives) + ": " +
                masSubs.toString() + "\n" +
                activity.resources?.getString(R.string.feminine_substantives) + ": " +
                femSubs.toString() + "\n" +
                activity.resources?.getString(R.string.neuter_substantives) + ": " +
                ntrSubs.toString() + "\n" +
                activity.resources?.getString(R.string.verbs) + ": " +
                verbs.toString() + "\n" +
                activity.resources?.getString(R.string.adjectives) + ": " +
                adjectives.toString() + "\n" +
                activity.resources?.getString(R.string.adverbs) + ": " +
                adverbs.toString() + "\n" +
                activity.resources?.getString(R.string.conjunctions) + ": " +
                conjunctions.toString() + "\n" +
                activity.resources?.getString(R.string.prepositions) + ": " +
                prepositions.toString() + "\n" +
                activity.resources?.getString(R.string.pronouns) + ": " +
                pronouns.toString() + "\n" +
                "\n" +
                activity.resources?.getString(R.string.total) + ": " +
                total.toString()
    }

    fun hasData(vocabularyType: String): Boolean {
        val records = if  (vocabularyType == "AllSubstantives") {
                        vocabularyDB.countElements("MasculineSubstantives") +
                        vocabularyDB.countElements("FeminineSubstantives") +
                        vocabularyDB.countElements("NeuterSubstantives")
                      } else {
                        vocabularyDB.countElements(vocabularyType)
                      }
        return records != 0
    }

    fun searchResultList(text: String): VocabularyList {
        lateinit var vocabularyList: VocabularyList

        vocabularyList = vocabularyDB.searchSubstantives(Gender.MASCULINE,text)
        vocabularyList.addAll(vocabularyDB.searchSubstantives(Gender.FEMININE,text))
        vocabularyList.addAll(vocabularyDB.searchSubstantives(Gender.NEUTER,text))
        vocabularyList.addAll(vocabularyDB.searchVerbs(text))
        vocabularyList.addAll(vocabularyDB.searchAdjectives(text))
        vocabularyList.addAll(vocabularyDB.searchAdverbs(text))
        vocabularyList.addAll(vocabularyDB.searchConjunction(text))
        vocabularyList.addAll(vocabularyDB.searchPreposition(text))

        return vocabularyList
    }
}