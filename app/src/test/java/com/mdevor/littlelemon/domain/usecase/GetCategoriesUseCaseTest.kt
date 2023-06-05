package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.testhelpers.stubs.getDomainMenuList
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCategoriesUseCaseTest {

    private val getCategoriesUseCase = GetCategoriesUseCase()

    @Test
    fun `GIVEN a menuList WHEN invoke THEN assert it returns expected unique list of categories`() {
        // GIVEN
        val menuList = getDomainMenuList() + getDomainMenuList()
        val expectedCategoryList = listOf("starters", "mains")

        // WHEN
        val actualList = getCategoriesUseCase(menuList)

        // THEN
        assertEquals(expectedCategoryList, actualList)
    }
}