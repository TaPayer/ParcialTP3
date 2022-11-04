package com.ar.ort.rickmorty.fragments

import android.os.Bundle
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
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {

    lateinit var vista: View
    lateinit var btnFav: Button
    lateinit var charImg: ImageView
    lateinit var charName: TextView
    lateinit var charStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista =  inflater.inflate(R.layout.fragment_detail, container, false)

        charName = vista.findViewById(R.id.character_name)
        charStatus = vista.findViewById(R.id.character_status)
        charImg = vista.findViewById(R.id.imageChar)
        btnFav = vista.findViewById(R.id.buttonFav)

        return vista
    }

    override fun onStart() {
        super.onStart()

        // LO QUE VIENE DEL LISTADO DE PRODUCTOS
        charName.text = DetailFragmentArgs.fromBundle(requireArguments()).character.charName
        val status = DetailFragmentArgs.fromBundle(requireArguments()).character.charStatus
        charStatus.text = "Estatus $status"
        val img = DetailFragmentArgs.fromBundle(requireArguments()).character.charImg

        Glide.with(vista)
            .load(img)
            .into(charImg)

        //VOLVER AL LISTADO
        btnFav.setOnClickListener{
           //aca agrega el personaje a la lista de favoritos
            Toast.makeText(getActivity(), "Agregado a Favoritos", Toast.LENGTH_SHORT).show();
        }


    }


}