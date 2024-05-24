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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mdevor.littlelemon.R

@Composable
fun HeroBannerContent() {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.displayLarge,
    )
    Text(
        text = stringResource(R.string.home_banner_subtitle),
        color = MaterialTheme.colorScheme.surface,
        style = MaterialTheme.typography.displayMedium
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.spacing_xx_large))
    ) {
        Text(
            text = stringResource(R.string.home_banner_description),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .weight(weight = 0.5f)
                .padding(end = dimensionResource(id = R.dimen.spacing_x_small)),
        )
        Image(
            painter = painterResource(R.drawable.hero_image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_medium)))
        )
    }
}