package com.mdevor.littlelemon.domain.model

data class MenuItem(
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
)