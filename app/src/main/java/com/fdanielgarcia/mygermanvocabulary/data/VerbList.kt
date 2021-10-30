package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Verb
import kotlin.random.Random

class VerbList() : VocabularyList(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun randomElement(): Verb {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)] as Verb
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VerbList> {
        override fun createFromParcel(parcel: Parcel): VerbList {
            return VerbList(parcel)
        }

        override fun newArray(size: Int): Array<VerbList?> {
            return arrayOfNulls(size)
        }
    }
}