package com.mdevor.littlelemon.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuListRequest(
    @SerialName("menu")
    val menuList: List<MenuItemRequest>,
)