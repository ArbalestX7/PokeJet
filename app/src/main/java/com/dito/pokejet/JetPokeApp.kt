package com.dito.pokejet

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dito.pokejet.data.Repository
import com.dito.pokejet.navigation.Screen
import com.dito.pokejet.ui.component.*
import com.dito.pokejet.ui.screen.aboutpage.AboutPageScreen
import com.dito.pokejet.ui.screen.home.HomeScreen
import com.dito.pokejet.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JetPokeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    //viewModel: PokeViewModel = viewModel(factory = ViewModelFactory(Repository()))
) {
    //val groupedPoke by viewModel.groupPoke.collectAsState()
    //val query by viewModel.query

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) {  innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.About.route){
                AboutPageScreen()
            }
        }
    }


    /*Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showBtn: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ){
            item{
                SearchPokeBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }
            groupedPoke.forEach{ (initial, pokemon) ->
                stickyHeader {
                    InitialHeader(char = initial)
                }
                items(pokemon, key = { it.id }) { poke ->
                    PokeListItem(
                        name = poke.name,
                        photoUrl = poke.photo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100))
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showBtn,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp, end = 30.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch{
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }*/
}











@Preview(showBackground = true)
@Composable
fun JetPokeAppPreview() {
    JetPokeApp()
}