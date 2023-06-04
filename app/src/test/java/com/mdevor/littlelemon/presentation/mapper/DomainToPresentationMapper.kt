package com.mdevor.littlelemon.presentation.mapper

import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.presentation.model.MenuItemData
import org.junit.Assert
import org.junit.Test

class DomainToPresentationMapper {

    @Test
    fun `GIVEN domain model WHEN toPresentation is called THEN should return expected presentation model`() {
        // Given
        val menuDomainModel = listOf(
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

        val expectedPresentationModel = listOf(
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


        // When
        val actualModel = menuDomainModel.toPresentation()

        // Then
        Assert.assertEquals(expectedPresentationModel, actualModel)
    }
}