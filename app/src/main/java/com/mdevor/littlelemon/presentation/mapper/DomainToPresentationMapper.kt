package com.mdevor.littlelemon.presentation.mapper

import com.mdevor.littlelemon.domain.entity.MenuEntity
import com.mdevor.littlelemon.presentation.model.MenuItemData

fun List<MenuEntity>.toPresentation() =
    map { it.toPresentation() }

private fun MenuEntity.toPresentation() =
    MenuItemData(
        title = title,
        description = description,
        price = "$${String.format("%.2f", price)}",
        category = category,
        image = image,
    )

