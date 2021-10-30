package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Conjunction
import kotlin.random.Random

class ConjunctionList() : VocabularyList(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun randomElement(): Conjunction {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)] as Conjunction
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConjunctionList> {
        override fun createFromParcel(parcel: Parcel): ConjunctionList {
            return ConjunctionList(parcel)
        }

        override fun newArray(size: Int): Array<ConjunctionList?> {
            return arrayOfNulls(size)
        }
    }
}