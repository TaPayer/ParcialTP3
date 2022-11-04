package com.ar.ort.rickmorty.Entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Character(val name: String, val status: String, val img: String) : Parcelable {

    val charName: String = name
    val charStatus: String = status
    val charImg: String = img

    init {

    }
}