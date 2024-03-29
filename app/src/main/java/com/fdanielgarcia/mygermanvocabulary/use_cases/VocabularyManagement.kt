package com.fdanielgarcia.mygermanvocabulary.use_cases

class VocabularyManagement (){
    fun removeSubstantivePlural(name: String): String {
        val pattern1 = "-.*$".toRegex()
        val pattern2 = " \\(no pl\\).*$".toRegex()
        val pattern3 = " \\(only pl\\).*$".toRegex()
        var nameWithoutPlural = pattern1.replace(name,"")
        nameWithoutPlural = pattern2.replace(nameWithoutPlural,"")
        nameWithoutPlural = pattern3.replace(nameWithoutPlural,"")

        return nameWithoutPlural
    }

    fun removeVerbHint(infinitive: String): String {
        val pattern1 = "-".toRegex()
        val pattern2 = " \\(.*$".toRegex()
        var infinitiveWithoutHint = pattern1.replace(infinitive,"")
        infinitiveWithoutHint = pattern2.replace(infinitiveWithoutHint,"")

        return infinitiveWithoutHint
    }
}