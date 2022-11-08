package com.ar.ort.rickmorty.Entities

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log

class SavedPreference(val context: Context) {
    private val SHARED_NAME = "RickyMorty"
    private val EMAIL = "email"
    private val USERNAME = "username"
    private var PHOTO = "photo"
    private var TIPOLISTA = "tipolista"
    private var BUSCADOR_HABILITADO = "buscador_habilitado"
    private var FAVORITOS_HABILITADO = "favoritos_habilitado"
    var favoritos: MutableList<Character> = ArrayList()
    val storage = context.getSharedPreferences(SHARED_NAME, 0)
    var arrPackage: HashSet<String> = hashSetOf()
    val set: MutableSet<String> = HashSet()

    fun setTipoLista(tipolista: String) {
        storage.edit().putString(TIPOLISTA, tipolista).apply()
    }

    fun getTipoLista(): String {
        return storage.getString(TIPOLISTA, "")!!
    }

    fun setBuscador(estado: String) {
        storage.edit().putString(BUSCADOR_HABILITADO, estado).apply()
    }

    fun getBuscador(): String {
        return storage.getString(BUSCADOR_HABILITADO, "")!!
    }

    fun setFavoritos(estado: String) {
        storage.edit().putString(FAVORITOS_HABILITADO, estado).apply()
    }

    fun getFavoritos(): String {
        return storage.getString(FAVORITOS_HABILITADO, "")!!
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

    fun agregarFavoritos(character: Character) {
        val set: Set<String> = storage.getStringSet("DATE_LIST", HashSet()) as Set<String>
        arrPackage.addAll(set)
        arrPackage.add(character.id.toString())
        packagesharedPreferences()
    }

    //METODO QUE PERSISTE LA LISTA DE IDCADA VEZ QUE SE MODIFICA
    private fun packagesharedPreferences() {
        val editor: SharedPreferences.Editor = storage.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(arrPackage)
        editor.putStringSet("DATE_LIST", set)
        editor.apply()
        Log.d("storesharedPreferences", "" + set)
    }

}