package com.mdevor.littlelemon.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mdevor.littlelemon.R

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 50.sp,
        color = LL_Yellow,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.markazi_regular)),
        fontSize = 30.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight(
            weight = 800
        )
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
        fontWeight = FontWeight(
            weight = 300
        )
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.karla_regular)),
        fontSize = 12.sp,
        fontWeight = FontWeight(700),
    )
)