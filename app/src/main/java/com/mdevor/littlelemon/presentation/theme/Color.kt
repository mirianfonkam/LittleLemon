package com.mdevor.littlelemon.presentation.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LL_Yellow = Color(0xFFF4CE14)
val LL_Grey_100 = Color(0xFFEDEFEE)
val LL_Grey_200 = Color(0xFFAFAFAF)
val LL_Green = Color(0xFF495E57)

val LightColorScheme = lightColorScheme(
    primary = LL_Yellow,
    secondary = LL_Green,
    surface = Color.White,
    surfaceVariant = LL_Grey_100,
    onSurface = Color.Black,
    onSurfaceVariant = LL_Grey_200
)
