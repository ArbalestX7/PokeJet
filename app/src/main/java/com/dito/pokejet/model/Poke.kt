package com.dito.pokejet.model


data class Poke(
    val pokeId: Long,
    val name: String,
    val desc: String,
    val photo: String,
    val height_weight: String,
    val type: String,
    val weak: String,
    val evo: String
)