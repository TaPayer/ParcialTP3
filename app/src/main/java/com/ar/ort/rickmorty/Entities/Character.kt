package com.ar.ort.rickmorty.Entities

class Character(name: String?, status: String?, species: String?, origin: String?) {

    var name: String = ""
    var status: String = ""
    var species: String = ""
    var origin: String = ""

    init {
        this.name = name!!
        this.status = status!!
        this.species = species!!
        this.origin = origin!!
    }
}