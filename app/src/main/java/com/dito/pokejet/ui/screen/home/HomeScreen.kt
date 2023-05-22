package com.dito.pokejet.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dito.pokejet.data.Repository
import com.dito.pokejet.model.OrderPoke
import com.dito.pokejet.ui.common.UiState
import com.dito.pokejet.ui.component.PokeListItem
import com.dito.pokejet.ui.component.ScrollToTopButton
import com.dito.pokejet.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Repository())),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPokemon()
            }
            is UiState.Success -> {
                HomeContent(
                    orderPoke = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderPoke: List<OrderPoke>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showBtn: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(orderPoke) { data ->
                PokeListItem(
                    id = data.poke.pokeId,
                    name = data.poke.name,
                    type = data.poke.type,
                    photoUrl = data.poke.photo,
                    navigateToDetail = navigateToDetail
                )
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
    }
}