package com.ar.ort.rickmorty.data

import com.google.gson.annotations.SerializedName

data class OriginData(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val url: String
)
