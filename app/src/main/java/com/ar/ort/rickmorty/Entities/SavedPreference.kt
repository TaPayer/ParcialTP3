package com.ar.ort.rickmorty.Entities

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.ar.ort.rickmorty.api.APIService
import com.ar.ort.rickmorty.data.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SavedPreference(val context: Context) {
    private val SHARED_NAME = "RickyMorty"
    private val EMAIL = "email"
    private val USERNAME = "username"
    private var PHOTO = "photo"
    private var TIPOLISTA = "tipolista"
    var favoritos: MutableList<Character> = ArrayList()
    val storage = context.getSharedPreferences(SHARED_NAME, 0)
    var arrPackage:  HashSet<String> = hashSetOf()
    val set: MutableSet<String> = HashSet()

    fun agregarFavoritos(character: Character ) {
        retriveSharedValue()
        if(arrPackage.isNotEmpty()){
            for (id in arrPackage) {
                if(id.toInt() != character.id){

                    arrPackage.add(character.id.toString())
                    packagesharedPreferences()
                }
            }
        }else{
            arrPackage.add(character.id.toString())
            packagesharedPreferences()
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

    private fun packagesharedPreferences() {
        val editor: SharedPreferences.Editor = storage.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(arrPackage)
        editor.putStringSet("DATE_LIST", set)
        editor.apply()
        Log.d("storesharedPreferences", "" + set)
    }

    fun retriveSharedValue() {
        val api = APIService.createAPI()
        val set: Set<String> = storage.getStringSet("DATE_LIST", HashSet()) as Set<String>
        arrPackage.addAll(set)
        for (id in arrPackage) {
            api.getCharacter("${APIService.BASE_URL}${id}")
                ?.enqueue(object : Callback<CharacterData?> {
                    override fun onResponse(call: Call<CharacterData?>, a: Response<CharacterData?>) {
                        val character: CharacterData? = (a.body())!!
                        if (character != null) {
                            val charaterToShow = Character(character.id, character.name, character.status, character.image, character.origin.name, character.species )
                            favoritos.add(charaterToShow)
                        }
                    }
                    override fun onFailure(call: Call<CharacterData?>, t: Throwable) {
                        Log.w("FAILURE", "Failure Call Get")
                    }
                })
        }
    }
}




