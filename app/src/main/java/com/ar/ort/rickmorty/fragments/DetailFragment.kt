package com.ar.ort.rickmorty.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.ar.ort.rickmorty.R
import com.ar.ort.rickmorty.activities.SplashActivity.Companion.prefs
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {

    lateinit var vista: View
    lateinit var btnFav: Button
    lateinit var charImg: ImageView
    lateinit var charName: TextView
    lateinit var charStatus: TextView
    lateinit var charOrigen: TextView
    lateinit var charEspecie: TextView
    lateinit var cruz: ImageView
    lateinit var unknow: ImageView
    lateinit var alive: ImageView


    var characters: MutableList<Character> = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_detail, container, false)

        cruz = vista.findViewById(R.id.cruz)
        unknow = vista.findViewById(R.id.unknow)
        alive = vista.findViewById(R.id.alive)
        charName = vista.findViewById(R.id.character_name)
        charStatus = vista.findViewById(R.id.character_status)
        charOrigen = vista.findViewById(R.id.character_origen)
        charEspecie = vista.findViewById(R.id.character_especie)
        charImg = vista.findViewById(R.id.imageChar)
        btnFav = vista.findViewById(R.id.buttonFav)

        return vista
    }

    override fun onStart() {
        super.onStart()

        // LO QUE VIENE DEL LISTADO DE PRODUCTOS
        val personaje = DetailFragmentArgs.fromBundle(requireArguments()).character

        charName.text = DetailFragmentArgs.fromBundle(requireArguments()).character.charName

        val origen = DetailFragmentArgs.fromBundle(requireArguments()).character.origen
        charOrigen.text = "Origen $origen"

        val especie = DetailFragmentArgs.fromBundle(requireArguments()).character.charEspecie
        charEspecie.text = "Especie $especie"

        val status = DetailFragmentArgs.fromBundle(requireArguments()).character.charStatus
        charStatus.text = "Status $status"

        if (status == "Dead") {
            cruz.visibility = View.VISIBLE
        }
        if (status == "unknown") {
            unknow.visibility = View.VISIBLE
        }
        if (status == "Alive") {
            alive.visibility = View.VISIBLE
        }

        val img = DetailFragmentArgs.fromBundle(requireArguments()).character.charImg
        Glide.with(vista)
            .load(img)
            .into(charImg)

        //VOLVER AL LISTADO
        btnFav.setOnClickListener {
            //aca agrega el personaje a la lista de favoritos
            Toast.makeText(getActivity(), "Agregado a Favoritos", Toast.LENGTH_SHORT).show();
            //Hardcodeado para testeo
            Log.i("PERSONAJE", "${personaje.charName}")

            prefs.agregarFavoritos(personaje)


        }
    }


}


