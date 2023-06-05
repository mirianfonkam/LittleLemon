package com.mdevor.littlelemon.testhelpers.stubs

import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.presentation.model.MenuItemData

fun getDomainMenuList(): List<MenuItem> =
    listOf(
        MenuItem(
            title = "Greek Salad",
            description = "Our delicious salad is served with Feta cheese and peeled cucumber. Includes tomatoes, onions, olives, salt and oregano in the ingredients.",
            image = "imageURL",
            category = "starters",
            price = 12.99,
        ),
        MenuItem(
            title = "Pasta",
            description = "Delicious pasta for your delight.",
            image = "imageURL",
            category = "mains",
            price = 6.99,
        )
    )

fun getPresentationMenuList(): List<MenuItemData> = listOf(
    MenuItemData(
        title = "Greek Salad",
        description = "Our delicious salad is served with Feta cheese and peeled cucumber. Includes tomatoes, onions, olives, salt and oregano in the ingredients.",
        image = "imageURL",
        category = "starters",
        price = "$12.99",
    ),
    MenuItemData(
        title = "Pasta",
        description = "Delicious pasta for your delight.",
        image = "imageURL",
        category = "mains",
        price = "$6.99",
    )
)

fun getCategoryList(): List<String> = listOf("starters", "mains")