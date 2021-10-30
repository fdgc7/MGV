package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Adverb
import kotlin.random.Random

class AdverbList() : VocabularyList(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun randomElement(): Adverb {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)] as Adverb
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AdverbList> {
        override fun createFromParcel(parcel: Parcel): AdverbList {
            return AdverbList(parcel)
        }

        override fun newArray(size: Int): Array<AdverbList?> {
            return arrayOfNulls(size)
        }
    }
}