package com.dicoding.subs_lazyadityarn.detail

import  androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.subs_lazyadityarn.model.Band
import com.dicoding.subs_lazyadityarn.model.BandData
import com.dicoding.subs_lazyadityarn.ui.theme.Subs_LazyAdityaRNTheme

// Halaman Detail Band

@Composable
fun DetailBandItems(
    bandId: Int,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val band: Band = BandData.detBands[bandId-1]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .padding(30.dp)
            ) {
                AsyncImage(
                    model = band.photoUrl,
                    contentDescription = "Foto Band",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(0.dp, 50.dp)
                        .padding(8.dp)
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(BorderStroke(1.dp, Color.Black), CircleShape)
                        .align(Alignment.BottomCenter)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = band.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = band.genre,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = band.album,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
        Card(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = band.descrip,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier

            )
        }
    }
}

@Preview
@Composable
fun DetailBandsItemsPrev() {
    Subs_LazyAdityaRNTheme() {

    }
}
