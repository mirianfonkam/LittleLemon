package com.mdevor.littlelemon.domain.entity

data class MenuItem(
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
)