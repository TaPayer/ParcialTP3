package com.ar.ort.rickmorty.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ar.ort.rickmorty.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navView: NavigationView
    private lateinit var drawer: DrawerLayout

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Buscar el nav host fragment de la vista - NavController
        navController = Navigation.findNavController(this, R.id.fragment_container_view)

        //Buscar el NavigationView y pasarlo al navController
        navView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)

        //buscar el DrawerLayout
        drawer = findViewById(R.id.drawer_layout_id)

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

        //Dejo un lisener para cuando se produce el cambio de destino unicamente me reemplace el icono.
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

    // MENU TRES PUNTITOS
    //override fun onCreateOptionsMenu(menu: Menu): Boolean {
    //   menuInflater.inflate(R.menu.menu_drawer, menu)
    //   return true
    // }
}