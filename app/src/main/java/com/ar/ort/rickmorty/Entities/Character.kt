package com.ar.ort.rickmorty.Entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Character(val id: Number, val name: String, val status: String, val img: String, val origen: String, val especie: String ) : Parcelable {

    val charId: Number = id
    val charName: String = name
    val charStatus: String = status
    val charImg: String = img
    val charOrigen: String = origen
    val charEspecie: String = especie

    init {

    }
}