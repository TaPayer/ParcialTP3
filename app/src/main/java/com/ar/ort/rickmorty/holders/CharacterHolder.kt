package com.ar.ort.rickmorty.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ar.ort.rickmorty.R
import com.bumptech.glide.Glide

class CharacterHolder(v: View) : RecyclerView.ViewHolder(v){

    private var view: View
    private lateinit var charImage: ImageView

    init{
        this.view = v
    }

    fun setName(name: String){
        val nameCh: TextView = view.findViewById(R.id.character_name)
        nameCh.text = name
    }

    fun setStatus(status: String){
        val statusCh: TextView = view.findViewById(R.id.character_status)
        statusCh.text = "Estatus: $status"
    }

    fun setImage(img: String) {
        charImage = view.findViewById(R.id.character_image)
        Glide.with(view)
            .load(img)
            .into(charImage)
    }

    fun getCardLayout(): CardView {
        return view.findViewById(R.id.card_package_item)
    }
}