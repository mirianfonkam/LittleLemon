package com.mdevor.littlelemon.data.mapper

import com.mdevor.littlelemon.data.remote.model.MenuItemRequest
import com.mdevor.littlelemon.data.remote.model.MenuListRequest
import com.mdevor.littlelemon.domain.entity.MenuEntity

fun MenuListRequest.toDomain(): List<MenuEntity> =
    menuList.map { it.toDomain() }

private fun MenuItemRequest.toDomain() =
    MenuEntity(
        title = title,
        description = description,
        price = price,
        category = category,
        image = image,
    )