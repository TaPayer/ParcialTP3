package com.ar.ort.rickmorty.holders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ar.ort.rickmorty.R

class CharacterHolder(v: View) : RecyclerView.ViewHolder(v){

    private var view: View

    init{
        this.view = v
    }

    fun setName(name: String){
        val txt: TextView = view.findViewById(R.id.character_name)
        txt.text = name
    }

    fun setStatus(status: String){
        val txt: TextView = view.findViewById(R.id.character_status)
        txt.text = status
    }

    fun getCardLayout(): CardView {
        return view.findViewById(R.id.card_package_item)
    }
}