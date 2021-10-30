package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Adjective
import kotlin.random.Random

class AdjectiveList() : VocabularyList(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun randomElement(): Adjective {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)] as Adjective
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AdjectiveList> {
        override fun createFromParcel(parcel: Parcel): AdjectiveList {
            return AdjectiveList(parcel)
        }

        override fun newArray(size: Int): Array<AdjectiveList?> {
            return arrayOfNulls(size)
        }
    }
}