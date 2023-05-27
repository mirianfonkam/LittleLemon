package com.mdevor.littlelemon.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = LL_Yellow,
    secondary = LL_Green,
    surface = Color.White,
    surfaceVariant = LL_Grey_100,
    onSurface = Color.Black,
    onSurfaceVariant = LL_Grey_200
)

@Composable
fun LittleLemonTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}