package com.mdevor.littlelemon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuLocalEntity(
    @PrimaryKey
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
)