package com.mdevor.littlelemon.presentation.components

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.mdevor.littlelemon.R

@Composable
fun LineDivider(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Divider(
        color = color,
        thickness = dimensionResource(id = R.dimen.spacing_line),
        modifier = modifier
    )
}