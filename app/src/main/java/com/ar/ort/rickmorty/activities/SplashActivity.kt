package com.ar.ort.rickmorty.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import com.ar.ort.rickmorty.R

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
}