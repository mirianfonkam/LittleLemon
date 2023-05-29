package com.mdevor.littlelemon.presentation.mapper

import com.mdevor.littlelemon.domain.model.MenuItem
import com.mdevor.littlelemon.domain.model.MenuItemList
import com.mdevor.littlelemon.presentation.model.MenuViewItem
import com.mdevor.littlelemon.presentation.model.MenuViewItemList

fun MenuItemList.toPresentation() =
    MenuViewItemList(
        menuList = menuList.map { it.toPresentation() }
    )

private fun MenuItem.toPresentation() =
    MenuViewItem(
        title = title,
        description = description,
        price = "$${String.format("%.2f", price)}",
        category = category,
        image = image,
    )

