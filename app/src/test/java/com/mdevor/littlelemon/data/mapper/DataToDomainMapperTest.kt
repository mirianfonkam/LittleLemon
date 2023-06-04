package com.mdevor.littlelemon.data.mapper

import com.mdevor.littlelemon.data.remote.model.MenuItemRequest
import com.mdevor.littlelemon.data.remote.model.MenuListRequest
import com.mdevor.littlelemon.domain.entity.MenuItem
import org.junit.Assert.assertEquals
import org.junit.Test

class DataToDomainMapperTest {

    @Test
    fun `GIVEN data model WHEN toDomain is called THEN should return expected domain model`() {
        // Given
        val menuDataModel = MenuListRequest(
            menuList = listOf(
                MenuItemRequest(
                    title = "Greek Salad",
                    description = "Our delicious salad is served with Feta cheese and peeled cucumber. Includes tomatoes, onions, olives, salt and oregano in the ingredients.",
                    image = "imageURL",
                    category = "starters",
                    price = 12.99,
                ),
                MenuItemRequest(
                    title = "Pasta",
                    description = "Delicious pasta for your delight.",
                    image = "imageURL",
                    category = "mains",
                    price = 6.99,
                )
            )
        )
        val expectedDomainModel = listOf(
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

        // When
        val actualModel = menuDataModel.toDomain()

        // Then
        assertEquals(expectedDomainModel, actualModel)
    }
}