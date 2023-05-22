package com.dito.pokejet.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dito.pokejet.R
import com.dito.pokejet.data.Repository
import com.dito.pokejet.model.Poke
import com.dito.pokejet.ui.common.UiState
import com.dito.pokejet.ui.theme.PokeJetTheme
import com.dito.pokejet.viewmodel.ViewModelFactory


@Composable
fun DetailScreen(
    pokeId: Long,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(Repository())),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPokeId(pokeId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.poke,
                    onBackClick = navigateBack,
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailContent(
    data: Poke,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box{
                AsyncImage(
                    model = data.photo,
                    contentDescription = "Photo Pokemon",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    modifier = modifier
                        .height(350.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = data.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = data.type,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = data.desc,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = stringResource(id = R.string.Weak),
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = data.weak,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = stringResource(id = R.string.height_weight),
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = data.height_weight,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Box {
                    AsyncImage(
                        model = data.evo,
                        contentDescription = "Photo Pokemon",
                        placeholder = painterResource(id = R.drawable.placeholder),
                        contentScale = ContentScale.Fit,
                        modifier = modifier
                            .height(300.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailPreview() {
    PokeJetTheme {
        DetailContent(
            data = Poke(
            2,
            "Absol",
            "Swift as the wind, Absol races through fields and mountains. Its curved, bow-like horn is acutely sensitive to the warning signs of natural disasters.",
            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/359.png",
            "3 ft 11 Inches / 103.6 lbs",
            "Dark",
            "Fairy, Bug, Fighting",
            "https://images2.imgbox.com/53/62/aDkscEiI_o.png"
        ),
            onBackClick = {},
        )
    }

}