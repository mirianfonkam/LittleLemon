package com.mdevor.littlelemon.data.mapper

import com.mdevor.littlelemon.data.remote.model.MenuItemRequest
import com.mdevor.littlelemon.data.remote.model.MenuListRequest
import com.mdevor.littlelemon.domain.entity.MenuItem

fun MenuListRequest.toDomain(): List<MenuItem> =
    menuList.map { it.toDomain() }

private fun MenuItemRequest.toDomain() =
    MenuItem(
        title = title,
        description = description,
        price = price,
        category = category,
        image = image,
    )