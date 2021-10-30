package com.fdanielgarcia.mygermanvocabulary.domain

import com.fdanielgarcia.mygermanvocabulary.R

enum class Gender(var article: String, val color: Int) {
    MASCULINE ("Der", R.color.der),
    FEMININE ("Die", R.color.die),
    NEUTER ("Das", R.color.das)
}