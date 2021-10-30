package com.fdanielgarcia.mygermanvocabulary.domain

data class Substantive(val name: String,
                       var gender: Gender,
                       var meaning: String
): Vocabulary
