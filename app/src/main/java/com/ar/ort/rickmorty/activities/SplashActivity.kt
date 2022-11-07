package com.ar.ort.rickmorty.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ar.ort.rickmorty.Entities.Character
import com.ar.ort.rickmorty.Entities.SavedPreference
import com.ar.ort.rickmorty.R
import com.ar.ort.rickmorty.api.APIService
import com.ar.ort.rickmorty.data.ServiceResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var splash: TextView
    lateinit var googleLogo: ImageView
    lateinit var btnContinuar: Button
    val Req_Code: Int = 123
    private val personajes = ArrayList<Character>()

    companion object {
        private const val SPLASH_TIME_OUT: Long = 10000 // 3 seconds
        lateinit var prefs: SavedPreference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        intent = Intent(this, MainActivity::class.java)
        callApi()

        prefs = SavedPreference(this)

        findViewById()

        Handler().postDelayed(
            {
                FirebaseApp.initializeApp(this)

                if (!prefs.getEmail().isEmpty()) {
                    //startActivity(Intent(this, MainActivity::class.java))

                    val bundle = Bundle()
                    bundle.putParcelableArrayList("personajes", personajes)
                    intent.putExtras(bundle)
                    startActivity(intent)
                    Log.w("PERSONAJESLOGUEADO", "$personajes")
                    prefs.setTipoLista("listaEntera")

                } else {
                    visibility()
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()

                    mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
                    firebaseAuth = FirebaseAuth.getInstance()

                    btnContinuar.setOnClickListener {
                        signInGoogle()
                        Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
                    }

                }



            }, SPLASH_TIME_OUT
        )
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                updateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                prefs.setEmail(account.email.toString())
                prefs.setUsername(account.displayName.toString())
                prefs.savePhoto(account.photoUrl)

              val bundle = Bundle()
                bundle.putParcelableArrayList("personajes", personajes)
                intent.putExtras(bundle)
                startActivity(intent)
                Log.w("PERSONAJESSINLOGUEAR", "$personajes")
                prefs.setTipoLista("listaEntera")
                finish()
            }
        }
    }

    private fun findViewById() {
        splash = findViewById(R.id.splash)
        googleLogo = findViewById(R.id.googleLogo)
        btnContinuar = findViewById(R.id.btnContinuar)
    }

    private fun visibility() {
        googleLogo.visibility = View.VISIBLE
        btnContinuar.visibility = View.VISIBLE
        splash.visibility = View.GONE
    }

    private fun callApi() {
        val api = APIService.createAPI()

        api.getCharacters()?.enqueue(object : Callback<ServiceResponse?> {

            override fun onResponse(
                call: Call<ServiceResponse?>,
                response: Response<ServiceResponse?>
            ) {
                val response: ServiceResponse? = (response.body())!!
                if (response != null) {
                    for (ch in response.results) {
                        personajes.add(Character(ch!!.id, ch!!.name, ch!!.status, ch!!.image,ch!!.origin.name, ch!!.species))
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

