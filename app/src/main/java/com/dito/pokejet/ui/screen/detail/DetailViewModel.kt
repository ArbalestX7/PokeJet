package com.dito.pokejet.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dito.pokejet.data.Repository
import com.dito.pokejet.model.OrderPoke
import com.dito.pokejet.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderPoke>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderPoke>> get() = _uiState

    fun getPokeId(pokeId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderById(pokeId))
        }
    }
}