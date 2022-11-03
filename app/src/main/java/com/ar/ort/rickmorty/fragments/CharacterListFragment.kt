package com.ar.ort.rickmorty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ar.ort.rickmorty.R
import com.ar.ort.rickmorty.Entities.Character
import com.ar.ort.rickmorty.adapters.CharacterListAdapter
import com.google.android.material.snackbar.Snackbar

class CharacterListFragment : Fragment() {

    lateinit var v: View
    lateinit var recCharacters: RecyclerView
    var characters: MutableList<Character> = ArrayList<Character>()

    private lateinit var linearlayoutManager: LinearLayoutManager
    private lateinit var characterListAdapter: CharacterListAdapter

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_character_list, container, false)
        recCharacters = v.findViewById(R.id.rec_characters)
        return v
    }

    override fun onStart(){
        super.onStart()

        //for(i in 1≤..≤10 ){
        //}

        //Hardcodeado para testeo
        //characters.add(Character( "Manzanares 3271", 1))
        //characters.add(Character( "Calle falsa 123", 2))
        //characters.add(Character( "Maipu 1855", 3))
        //characters.add(Character( "Sarlanga 32C", 4))

        recCharacters.setHasFixedSize(true)
        linearlayoutManager = LinearLayoutManager(context)
        recCharacters.layoutManager = linearlayoutManager
        characterListAdapter = CharacterListAdapter(characters){ x ->
            onItemClick(x)
        }
    }

    fun onItemClick( position : Int) : Boolean{
        Snackbar.make(v,position.toString(), Snackbar.LENGTH_SHORT).show()
        return true
    }
}


