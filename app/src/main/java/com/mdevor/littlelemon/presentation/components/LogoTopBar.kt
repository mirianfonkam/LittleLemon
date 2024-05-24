package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mdevor.littlelemon.R

@Composable
fun LogoTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .padding(
                    dimensionResource(id = R.dimen.spacing_small)
                )
                .size(height = 40.dp, width = 148.dp),
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.little_lemon_logo_content_desc)
        )
    }
}