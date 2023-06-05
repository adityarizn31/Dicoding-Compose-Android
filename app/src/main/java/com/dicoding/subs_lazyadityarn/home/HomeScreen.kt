package com.dicoding.subs_lazyadityarn.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.subs_lazyadityarn.JetBandsViewModel
import com.dicoding.subs_lazyadityarn.ViewModelFactory
import com.dicoding.subs_lazyadityarn.data.BandRepository
import com.dicoding.subs_lazyadityarn.model.BandData.detBands
import com.dicoding.subs_lazyadityarn.ui.theme.Subs_LazyAdityaRNTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModelFactory: JetBandsViewModel = viewModel(factory = ViewModelFactory(BandRepository())),
) {

    val groupedBands by viewModelFactory.groupedAgents.collectAsState()

    Box(modifier = modifier) {

        // Digunakan untuk Melakukan Scroll
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        // Digunakan untuk Menampilkan Data
        LazyColumn(
            state = listState,
            // Digunakan untuk tidak menutupi item akhir
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(detBands, key = { it.id }) { bandss ->
                BandListItem(
                    name = bandss.name,
                    genre = bandss.genre,
                    photoUrl = bandss.photoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToDetail(bandss.id)
                        }
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}


@Composable
fun BandListItem(
    name: String,
    genre: String,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Row() {
                Text(
                    text = name,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 16.dp)

                )
                Text(
                    text = genre,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

// Digunakan untuk Scroll Button
@Composable
fun ScrollButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .shadow(elevation = 10.dp, shape = CircleShape)
            .clip(shape = CircleShape)
            .size(56.dp),
        colors = ButtonDefaults.buttonColors(
            Color.Black,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = "Icon Arah Atas",
            modifier = modifier
                .size(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BandListItemPreview() {
    Subs_LazyAdityaRNTheme() {
        BandListItem(
            name = "Guns N Roses",
            genre = "Metal",
            photoUrl = "https://raw.githubusercontent.com/adityarizn31/RawImage/main/g1.png"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenView() {

}