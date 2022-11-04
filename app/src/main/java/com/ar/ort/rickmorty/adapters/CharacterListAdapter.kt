package com.ar.ort.rickmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar.ort.rickmorty.R
import com.ar.ort.rickmorty.holders.CharacterHolder
import com.ar.ort.rickmorty.Entities.Character

class CharacterListAdapter(
    var charactersList: MutableList<Character>,
    val onItemClick: (Int) -> Boolean
): RecyclerView.Adapter<CharacterHolder>(){

    override fun getItemCount(): Int{
        return charactersList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character,parent,false)
        return (CharacterHolder(view))
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.setName(charactersList[position].charName)
        holder.setStatus(charactersList[position].charStatus)
        holder.setOrigen(charactersList[position].charOrigen)
        holder.setImage(charactersList[position].charImg)
        holder.setEspecie(charactersList[position].charEspecie)

        holder.getCardLayout().setOnClickListener{
            onItemClick(position)
        }
    }

    fun setData(newData: ArrayList<Character>){
        this.charactersList = newData
        this.notifyDataSetChanged()
    }
}