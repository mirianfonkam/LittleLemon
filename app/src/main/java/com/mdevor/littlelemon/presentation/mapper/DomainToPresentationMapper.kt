package com.mdevor.littlelemon.presentation.mapper

import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.presentation.model.MenuItemData

fun List<MenuItem>.toPresentation() =
    map { it.toPresentation() }

private fun MenuItem.toPresentation() =
    MenuItemData(
        title = title,
        description = description,
        price = "$${String.format("%.2f", price)}",
        category = category,
        image = image,
    )

