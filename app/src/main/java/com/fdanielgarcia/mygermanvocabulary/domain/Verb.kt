package com.fdanielgarcia.mygermanvocabulary.domain

data class Verb(val infinitive: String,
                var present: String,
                var past: String,
                var perfect: String,
                var meaning: String
): Vocabulary
