package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Vocabulary

abstract class VocabularyList : Parcelable {
    val list = mutableListOf<Vocabulary>()

    fun element(id: Int): Vocabulary {
        return list[id]
    }

    abstract fun randomElement(): Vocabulary

    private fun allElements(): List<Vocabulary> {
        return list
    }

    fun add(vocabulary: Vocabulary) {
        list.add(vocabulary)
    }

    fun addAll(vocabularyList: VocabularyList) {
        list.addAll(vocabularyList.allElements())
    }

    fun update(id: Int, vocabulary: Vocabulary) {
        list[id] = vocabulary
    }

    fun delete(id: Int) {
        list.removeAt(id)
    }

    fun size(): Int {
        return list.size
    }
}