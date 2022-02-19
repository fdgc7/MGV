package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Pronoun
import kotlin.random.Random

class PronounList() : VocabularyList(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun randomElement(): Pronoun {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)] as Pronoun
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PronounList> {
        override fun createFromParcel(parcel: Parcel): PronounList {
            return PronounList(parcel)
        }

        override fun newArray(size: Int): Array<PronounList?> {
            return arrayOfNulls(size)
        }
    }
}