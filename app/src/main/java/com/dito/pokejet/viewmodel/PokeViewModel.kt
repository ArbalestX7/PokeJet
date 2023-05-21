package com.dito.pokejet.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dito.pokejet.data.Repository
import com.dito.pokejet.model.Poke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/*class PokeViewModel (private val repository: Repository) : ViewModel() {
    private val _groupPoke = MutableStateFlow(
        repository.getPokemon()
            .sortedBy { it.name }
            .groupBy { it.name [0] }
    )
    val groupPoke: StateFlow<Map<Char,List<Poke>>> get() = _groupPoke

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupPoke.value = repository.searchPokemon(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name [0] }
    }
}*/