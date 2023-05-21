package com.dito.pokejet.data

import com.dito.pokejet.model.OrderPoke
import com.dito.pokejet.model.Poke
import com.dito.pokejet.model.PokeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val pokemons = mutableListOf<OrderPoke>()

    init {
        if (pokemons.isEmpty()) {
            PokeData.pokemon.forEach{
                pokemons.add(OrderPoke(it,0))
            }
        }
    }
    fun getPokemon(): Flow<MutableList<OrderPoke>> {
        return flowOf(pokemons)
    }

    fun searchPokemon(query: String): List<Poke> {
        return PokeData.pokemon.filter{
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getOrderById(id: Long): OrderPoke {
        return pokemons.first() {
            it.poke.id == id
        }
    }
}