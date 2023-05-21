package com.dito.pokejet.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    //object Detail : Screen("detail")
    object About : Screen("about_page")
}