package com.dito.pokejet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dito.pokejet.data.Repository
import com.dito.pokejet.ui.screen.home.HomeViewModel

class ViewModelFactory (
    private val repository: Repository
    ): ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class: " + modelClass.name)
    }
}