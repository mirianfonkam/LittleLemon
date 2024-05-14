package com.mdevor.littlelemon.presentation.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.mdevor.littlelemon.R

@Composable
fun LineDivider(
    modifier: Modifier = Modifier,
    color: Color,
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = dimensionResource(id = R.dimen.spacing_line),
        color = color
    )
}