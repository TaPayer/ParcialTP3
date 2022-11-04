package com.ar.ort.rickmorty.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ar.ort.rickmorty.Entities.Character
import com.ar.ort.rickmorty.R
import com.ar.ort.rickmorty.adapters.CharacterListAdapter


class HomeFragment : Fragment() {

    lateinit var v: View

    lateinit var recCharacters: RecyclerView
    var characters: MutableList<Character> = ArrayList<Character>()

    private lateinit var linearlayoutManager: LinearLayoutManager
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var gridLayoutManager :GridLayoutManager

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false)
        recCharacters = v.findViewById(R.id.rec_characters)
        return v
    }

    override fun onStart(){
        super.onStart()

        //Hardcodeado para testeo
        for (i in 1..10) {
            characters.add(Character("Rickanmorti", "Alive", "https://picsum.photos/id/$i/200/200"))
        }

        recCharacters.setHasFixedSize(true)

        //linearlayoutManager = LinearLayoutManager(context)

        val numberOfColumns = 2
        gridLayoutManager = GridLayoutManager(context, numberOfColumns)



        recCharacters.layoutManager = gridLayoutManager

        recCharacters.adapter = CharacterListAdapter(characters){ x ->
            onItemClick(x)
        }
    }

    override fun onPause() {
        super.onPause()
        characters.clear()

    }

    fun onItemClick( position : Int) : Boolean{
        // PARA NAVEGAR A LA VENTANA DE DETALLE
        val character = characters[position]
            Log.d(character.name.toString(), "QUE ES PRODUCTO")
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(character)
            view?.findNavController()?.navigate(action)

        return true
    }
}


