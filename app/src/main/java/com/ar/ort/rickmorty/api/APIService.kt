package com.ar.ort.rickmorty.api
import com.ar.ort.rickmorty.interfaces.serviceAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/character/"
        fun createAPI(): serviceAPI {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceAPI::class.java)
        }
    }
}
    /*
    private fun callApi() {
        val api = APIService.createAPI()

        api.getCharacters()?.enqueue(object : Callback<ArrayList<ServiceResponse?>?> {
            override fun onResponse(
                call: Call<ServiceResponse?>,
                response: Response<ServiceResponse?>
            ) {
                val response: ServiceResponse? = (response.body())!!
                Log.w("SPLASH", "$response")
                if (response != null) {
                    Log.d("RESPONSE ", response.toString())
                    for(character in response){
                        //markers.add(DeaMarker(dea!!.id, dea!!.latitude.value.toDouble(), dea!!.longitude.value.toDouble(), dea!!.active.value, dea!!.datestamp.value, dea!!.address.value))
                    }
                }
            }
     */