package com.ar.ort.rickmorty.interfaces
import com.ar.ort.rickmorty.data.ServiceResponse
import retrofit2.Call
import retrofit2.http.GET

interface serviceAPI {
    @GET(".")
    fun getCharacters(
    ): Call<ServiceResponse?>?
}