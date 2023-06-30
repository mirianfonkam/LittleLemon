package com.mdevor.littlelemon.testhelpers.stubs

import com.mdevor.littlelemon.domain.entity.MenuEntity
import com.mdevor.littlelemon.presentation.model.MenuItemData

fun getDomainMenuList(): List<MenuEntity> =
    listOf(
        MenuEntity(
            title = "Greek Salad",
            description = "Our delicious salad is served with Feta cheese and peeled cucumber. Includes tomatoes, onions, olives, salt and oregano in the ingredients.",
            image = "imageURL",
            category = "starters",
            price = 12.99,
        ),
        MenuEntity(
            title = "Pasta",
            description = "Delicious pasta for your delight.",
            image = "imageURL",
            category = "mains",
            price = 6.99,
        )
    )

fun getPresentationMenuList(): List<MenuItemData> = listOf(
    greekSaladItem(),
    MenuItemData(
        title = "Pasta",
        description = "Delicious pasta for your delight.",
        image = "imageURL",
        category = "mains",
        price = "$6.99",
    )
)

fun greekSaladItem() = MenuItemData(
    title = "Greek Salad",
    description = "Our delicious salad is served with Feta cheese and peeled cucumber. Includes tomatoes, onions, olives, salt and oregano in the ingredients.",
    image = "imageURL",
    category = "starters",
    price = "$12.99",
)

fun getCategoryList(): List<String> = listOf("starters", "mains")