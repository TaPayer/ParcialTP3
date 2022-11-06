package com.ar.ort.rickmorty.Entities

import android.content.Context
import android.net.Uri

class SavedPreference(val context: Context) {
    private val SHARED_NAME = "RickyMorty"
    private val EMAIL = "email"
    private val USERNAME = "username"
    private var PHOTO = "photo"
    private var TIPOLISTA = "tipolista"
    var favoritos: MutableList<Character> = ArrayList()

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun agregarFavoritos(character: Character ) {
        if(favoritos.isNotEmpty()){
            for (personaje in favoritos) {
                if(personaje.charName != character.charName){
                    favoritos.add(character)
                }
            }
        }else{
            favoritos.add(character)
        }
    }


    fun setTipoLista(tipolista: String) {
        storage.edit().putString(TIPOLISTA, tipolista).apply()
    }

    fun getTipoLista(): String {
        return storage.getString(TIPOLISTA, "")!!
    }

    fun setEmail(email: String) {
        storage.edit().putString(EMAIL, email).apply()
    }

    fun getEmail(): String {
        return storage.getString(EMAIL, "")!!
    }

    fun setUsername(lastname: String) {
        storage.edit().putString(USERNAME, lastname).apply()
    }

    fun getUsername(): String {
        return storage.getString(USERNAME, "")!!
    }

    fun savePhoto(photo: Uri?) {
        storage.edit().putString(PHOTO, photo.toString()).apply()
    }
}




