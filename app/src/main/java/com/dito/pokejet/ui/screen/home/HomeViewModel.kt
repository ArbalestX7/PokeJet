package com.dito.pokejet.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dito.pokejet.data.Repository
import com.dito.pokejet.model.OrderPoke
import com.dito.pokejet.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderPoke>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderPoke>>> get() = _uiState

    fun getPokemon() {
        viewModelScope.launch {
            repository.getPokemon()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { pokemons ->
                    _uiState.value = UiState.Success(pokemons)
                }
        }
    }
}