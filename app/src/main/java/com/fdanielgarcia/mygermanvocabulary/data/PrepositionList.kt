package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Preposition
import kotlin.random.Random

class PrepositionList() : VocabularyList(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun randomElement(): Preposition {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)] as Preposition
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PrepositionList> {
        override fun createFromParcel(parcel: Parcel): PrepositionList {
            return PrepositionList(parcel)
        }

        override fun newArray(size: Int): Array<PrepositionList?> {
            return arrayOfNulls(size)
        }
    }
}