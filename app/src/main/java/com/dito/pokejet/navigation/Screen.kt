package com.dito.pokejet.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object About : Screen("about_page")
    object DetailPoke : Screen("home/{pokeId}") {
        fun createRoute(pokeId: Long) = "home/$pokeId"
    }
}