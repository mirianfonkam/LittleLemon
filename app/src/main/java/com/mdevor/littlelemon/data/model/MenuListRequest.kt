package com.mdevor.littlelemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuListRequest(
    @SerialName("menu")
    val menuList: List<MenuItemRequest>
)