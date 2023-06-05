package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mdevor.littlelemon.R

@Composable
fun HeroBannerContent() {
    Text(
        text = "Little Lemon",
        style = MaterialTheme.typography.displayLarge,
    )
    Text(
        text = "Chicago",
        color = MaterialTheme.colorScheme.surface,
        style = MaterialTheme.typography.displayMedium
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 48.dp)
    ) {
        Text(
            text = "We are a family owned Mediterranean restaurant, " +
                    "focused on traditional recipes served with a modern twist.",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .weight(weight = 0.5f)
                .padding(end = 8.dp),
        )
        Image(
            painter = painterResource(R.drawable.hero_image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}