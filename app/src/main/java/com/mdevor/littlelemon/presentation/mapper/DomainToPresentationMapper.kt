package com.mdevor.littlelemon.presentation.mapper

import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.domain.entity.MenuEntity
import com.mdevor.littlelemon.domain.entity.UserEntity
import com.mdevor.littlelemon.presentation.model.InfoData
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


fun UserEntity.toPresentation(): List<InfoData> {
    return listOf(
        InfoData(
            label = R.string.first_name,
            value = firstName
        ),
        InfoData(
            label = R.string.last_name,
            value = lastName
        ),
        InfoData(
            label = R.string.email,
            value = email
        ),
    )
}
