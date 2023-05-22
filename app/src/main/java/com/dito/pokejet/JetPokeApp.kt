package com.dito.pokejet

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dito.pokejet.navigation.Screen
import com.dito.pokejet.ui.component.*
import com.dito.pokejet.ui.screen.aboutpage.AboutPageScreen
import com.dito.pokejet.ui.screen.detail.DetailScreen
import com.dito.pokejet.ui.screen.home.HomeScreen

@Composable
fun JetPokeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val topBarState = rememberSaveable { (mutableStateOf(true)) }


    when (currentRoute) {
        Screen.Home.route -> {
            topBarState.value = true
        }
        Screen.DetailPoke.route -> {
            topBarState.value = false
        }
        Screen.About.route -> {
            topBarState.value = false
        }
    }
    Scaffold(
        topBar = {
                 TopHomeBar(
                     topBarState = topBarState
                 )
        },
        bottomBar = {
            if (currentRoute != Screen.DetailPoke.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {  innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { pokeId ->
                        navController.navigate(Screen.DetailPoke.createRoute(pokeId))
                    }
                )
            }
            composable(Screen.About.route){
                AboutPageScreen()
            }
            composable(
                route = Screen.DetailPoke.route,
                arguments = listOf(navArgument("pokeId") {type = NavType.LongType}),
            ) {
                val id = it.arguments?.getLong("pokeId") ?: -1L
                DetailScreen(
                    pokeId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun JetPokeAppPreview() {
    JetPokeApp()
}