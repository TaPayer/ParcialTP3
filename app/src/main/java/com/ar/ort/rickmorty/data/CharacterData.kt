package com.ar.ort.rickmorty.data

import com.google.gson.annotations.SerializedName

data class CharacterData(
    @field:SerializedName("id") val id: Number,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("species") val species: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("origin") val origin: OriginData,
)
