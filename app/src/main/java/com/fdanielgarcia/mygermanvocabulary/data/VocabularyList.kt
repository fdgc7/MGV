package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Verb
import com.fdanielgarcia.mygermanvocabulary.domain.Vocabulary
import kotlin.random.Random

open class VocabularyList() : Parcelable {
    val list = mutableListOf<Vocabulary>()

    constructor(parcel: Parcel) : this() {
    }

    fun element(id: Int): Vocabulary {
        return list[id]
    }

    open fun randomElement(): Vocabulary {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)]
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VocabularyList> {
        override fun createFromParcel(parcel: Parcel): VocabularyList {
            return VocabularyList(parcel)
        }

        override fun newArray(size: Int): Array<VocabularyList?> {
            return arrayOfNulls(size)
        }
    }
}