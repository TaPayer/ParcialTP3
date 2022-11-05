package com.ar.ort.rickmorty.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ar.ort.rickmorty.Entities.Character
import com.ar.ort.rickmorty.Entities.SavedPreference
import com.ar.ort.rickmorty.R
import com.ar.ort.rickmorty.activities.SplashActivity.Companion.prefs
import com.ar.ort.rickmorty.fragments.HomeFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navView: NavigationView
    private lateinit var drawer: DrawerLayout
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var personajes: ArrayList<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        personajes =
            intent.getParcelableArrayListExtra<Character>("personajes") as ArrayList<Character>

        //Buscar el nav host fragment de la vista - NavController
        navController = Navigation.findNavController(this, R.id.fragment_container_view)

        //Buscar el NavigationView y pasarlo al navController
        navView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)

        //buscar el DrawerLayout
        drawer = findViewById(R.id.drawer_layout_id)
        navigate()


        //menu hamburguesa -> comentado para que no aparezca la flecha
        //NavigationUI.setupActionBarWithNavController(this,navController,drawer)

        //Creo una configuración para Toolbar
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(),
            /*topLevelDestinationIds = setOf(
                R.id.fragment1,
                R.id.fragment2,
                R.id.fragment3
            ),*/
            fallbackOnNavigateUpListener = ::onSupportNavigateUp

        )
        //Seteo la configuración
        setupActionBarWithNavController(navController, appBarConfiguration)

        //Dejo un listener para cuando se produce el cambio de destino unicamente me reemplace el icono.
        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburguer)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        //Fuezo al boton de navegación de la toolbar que solo abra el menú Drawer
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            drawer.openDrawer(GravityCompat.START)
        }

        //Cancelo la navegación
        //return NavigationUI.navigateUp(navController,drawer)
        return false

    }

    fun getCharacters(): ArrayList<Character> {
        return this.personajes
    }

    private fun logOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient.signOut().addOnCompleteListener {
            val intent = Intent(this, SplashActivity::class.java)
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            SplashActivity.prefs.setEmail("")
            SplashActivity.prefs.setUsername("")
            startActivity(intent)
            finish()
        }
    }

    private fun navigate() {
        navView.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            drawer.closeDrawer(GravityCompat.START)
            when (id) {
                R.id.homeFragment -> {
                    Navigation.findNavController(this, R.id.fragment_container_view)
                        .navigate(R.id.homeFragment)

                    prefs.setTipoLista("listaEntera")
                    Log.d("PERSONAMANACTVITIARGS", "listaEntera")
                    true
                }

                R.id.favoritesFragment -> {
                    Navigation.findNavController(this, R.id.fragment_container_view)
                        .navigate(R.id.homeFragment)
                    prefs.setTipoLista("favoritos")
                    Log.d("PERSONAMANACTVITIARGS", "favoritos")
                    true
                }
                R.id.settingsFragment -> {
                    Navigation.findNavController(this, R.id.fragment_container_view)
                        .navigate(R.id.settingsFragment)
                    true
                }
                R.id.loginFragment -> {
                    logOut()
                    true
                }
                else -> {
                    false
                }

            }
        }
    }

}

// MENU TRES PUNTITOS
//override fun onCreateOptionsMenu(menu: Menu): Boolean {
//   menuInflater.inflate(R.menu.menu_drawer, menu)
//   return true
// }