package com.mdevor.littlelemon.presentation.components

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LineDivider(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Divider(
        color = color,
        thickness = 1.dp,
        modifier = modifier
    )
}