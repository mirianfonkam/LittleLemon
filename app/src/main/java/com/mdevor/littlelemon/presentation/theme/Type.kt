package com.mdevor.littlelemon.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.mdevor.littlelemon.R

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 60.sp,
        color = LL_Yellow,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 40.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
        color = Color.White,
    )
)