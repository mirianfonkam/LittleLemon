package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
fun ProfileTopBar(
    onProfileClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        Image(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.spacing_x_small))
                .size(size = 40.dp)
                .clip(CircleShape)
                .clickable { onProfileClick() },
            painter = painterResource(R.drawable.ic_little_lemon_profile),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.navigate_to_profile_content_desc)
        )
    }
}