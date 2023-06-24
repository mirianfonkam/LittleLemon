package com.mdevor.littlelemon.presentation.model

import androidx.annotation.DrawableRes

data class UserData(
    val firstName: String,
    val lastName: String,
    val email: String,
    @DrawableRes
    val image: Int,
)
