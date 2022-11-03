package com.ar.ort.rickmorty.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import com.ar.ort.rickmorty.R
import com.ar.ort.rickmorty.api.APIService
import com.ar.ort.rickmorty.data.ServiceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_TIME_OUT:Long = 10000 // 3 seconds
    }

    lateinit var splash : TextView
    lateinit var logoApp: ImageView
    lateinit var bienvenidos : TextView
    lateinit var marca : TextView
    lateinit var input_user : EditText
    lateinit var input_pass : EditText
    lateinit var btnContinuar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.w("ACA", "llamada")
        callApi()
        splash = findViewById(R.id.splash)

        logoApp = findViewById(R.id.logoApp)
        bienvenidos = findViewById(R.id.bienvenidos)
        marca = findViewById(R.id.marca)
        input_user= findViewById(R.id.input_user)
        input_pass= findViewById(R.id.input_pass)
        btnContinuar = findViewById(R.id.btnContinuar)

        Handler().postDelayed(
            {
                logoApp.visibility = View.VISIBLE
                bienvenidos.visibility = View.VISIBLE
                marca.visibility = View.VISIBLE
                input_user.visibility = View.VISIBLE
                input_pass.visibility = View.VISIBLE
                btnContinuar.visibility = View.VISIBLE
                splash.visibility = View.GONE

            }
            , SPLASH_TIME_OUT)



        btnContinuar.setOnClickListener {


            startActivity(Intent(this, MainActivity::class.java))
            //finish()


        }
    }

    private fun callApi() {
        val api = APIService.createAPI()

        api.getCharacters()?.enqueue(object : Callback<ServiceResponse?> {
            override fun onResponse(
                call: Call<ServiceResponse?>,
                response: Response<ServiceResponse?>
            ) {
                val response: ServiceResponse? = (response.body())!!
                Log.w("SPLASH LLAMADA", "$response")
                if (response != null) {
                    for (character in response.results) {
                        //markers.add(DeaMarker(dea!!.id, dea!!.latitude.value.toDouble(), dea!!.longitude.value.toDouble(), dea!!.active.value, dea!!.datestamp.value, dea!!.address.value))
                    }
                }
            }

            override fun onFailure(call: Call<ServiceResponse?>, t: Throwable) {
                Toast.makeText (applicationContext,
                    "Se ha producido un error de carga",
                    Toast.LENGTH_SHORT)
                    .show();
            }
        })
    }
}