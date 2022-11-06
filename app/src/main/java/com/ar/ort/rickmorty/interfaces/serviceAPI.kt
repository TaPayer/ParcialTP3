package com.ar.ort.rickmorty.interfaces
import com.ar.ort.rickmorty.data.CharacterData
import com.ar.ort.rickmorty.data.ServiceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface serviceAPI {
    @GET(".")
    fun getCharacters(
    ): Call<ServiceResponse?>?

    @GET
    fun getCharacter(@Url url: String
    ): Call<CharacterData?>?

}