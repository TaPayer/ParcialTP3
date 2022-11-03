package com.ar.ort.rickmorty.data
import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    @field:SerializedName("results") val results: ArrayList<CharacterData>
)
