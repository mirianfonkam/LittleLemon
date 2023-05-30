package com.mdevor.littlelemon.presentation.mapper

import com.mdevor.littlelemon.domain.model.MenuItem
import com.mdevor.littlelemon.domain.model.MenuItemList
import com.mdevor.littlelemon.presentation.model.MenuItemData
import com.mdevor.littlelemon.presentation.model.MenuItemListData

fun MenuItemList.toPresentation() =
    MenuItemListData(
        menuList = menuList.map { it.toPresentation() }
    )

private fun MenuItem.toPresentation() =
    MenuItemData(
        title = title,
        description = description,
        price = "$${String.format("%.2f", price)}",
        category = category,
        image = image,
    )

