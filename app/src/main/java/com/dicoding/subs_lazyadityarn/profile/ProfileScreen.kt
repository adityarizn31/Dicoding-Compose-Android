package com.dicoding.subs_lazyadityarn.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.subs_lazyadityarn.R
import com.dicoding.subs_lazyadityarn.ui.theme.Subs_LazyAdityaRNTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.adit),
            contentDescription = "profile_picture",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(300.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(20.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_person),
                contentDescription = "Icon Email",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .padding(10.dp)
            )
            Text(
                text = "Aditya Rizkiawan Nugraha",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_email),
                contentDescription = "Icon Email",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .padding(10.dp)
            )
            Text(
                text = "adityarizkiawann@gmail.com",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = modifier
                    .padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Subs_LazyAdityaRNTheme {
        ProfileScreen()
    }
}