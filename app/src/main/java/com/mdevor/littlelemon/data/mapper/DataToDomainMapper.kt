package com.mdevor.littlelemon.data.mapper

import com.mdevor.littlelemon.data.model.MenuItemRequest
import com.mdevor.littlelemon.data.model.MenuListRequest
import com.mdevor.littlelemon.domain.model.MenuItem
import com.mdevor.littlelemon.domain.model.MenuItemList

fun MenuListRequest.toDomain(): MenuItemList {
    return MenuItemList(
        menuList = menuList.map { it.toDomain() }
    )
}

private fun MenuItemRequest.toDomain(): MenuItem {
    return MenuItem(
        title = title,
        description = description,
        price = price,
        category = category,
        image = image,
    )
}