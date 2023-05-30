package com.mdevor.littlelemon.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class MenuItemData(
    val title: String,
    val description: String,
    val price: String,
    val category: String,
    val image: String,
)