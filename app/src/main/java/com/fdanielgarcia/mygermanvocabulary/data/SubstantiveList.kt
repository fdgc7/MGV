package com.fdanielgarcia.mygermanvocabulary.data

import android.os.Parcel
import android.os.Parcelable
import com.fdanielgarcia.mygermanvocabulary.domain.Substantive
import kotlin.random.Random

class SubstantiveList() : VocabularyList(), Parcelable {
    constructor(parcel: Parcel) : this()

    override fun randomElement(): Substantive {
        val rand = Random(System.nanoTime())
        return list[(0 until list.size - 1).random(rand)] as Substantive
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SubstantiveList> {
        override fun createFromParcel(parcel: Parcel): SubstantiveList {
            return SubstantiveList(parcel)
        }

        override fun newArray(size: Int): Array<SubstantiveList?> {
            return arrayOfNulls(size)
        }
    }
}