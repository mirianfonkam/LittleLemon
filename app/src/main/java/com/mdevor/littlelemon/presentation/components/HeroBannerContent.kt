package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme

@Composable
fun HeroBannerContent() {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.displayLarge,
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.spacing_medium))
    ) {
        Column(
            modifier = Modifier.weight(weight = 0.5f)
        ) {
            Text(
                text = stringResource(R.string.home_banner_subtitle),
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.spacing_x_small)),
                text = stringResource(R.string.home_banner_description),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.surface,
            )
        }
        Image(
            modifier = Modifier
                .size(dimensionResource(R.dimen.hero_banner_image_size))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.spacing_medium))),
            painter = painterResource(R.drawable.hero_image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeroBannerContentPreview() {
    LittleLemonTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_small))
                .fillMaxWidth(),
        ) {
            HeroBannerContent()
        }
    }
}