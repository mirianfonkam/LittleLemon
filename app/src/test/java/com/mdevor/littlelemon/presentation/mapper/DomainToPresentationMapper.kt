package com.mdevor.littlelemon.presentation.mapper

import com.mdevor.littlelemon.stubs.getDomainMenuList
import com.mdevor.littlelemon.stubs.getPresentationMenuList
import org.junit.Assert
import org.junit.Test

class DomainToPresentationMapper {

    @Test
    fun `GIVEN domain model WHEN toPresentation is called THEN should return expected presentation model`() {
        // Given
        val menuDomainModel = getDomainMenuList()

        val expectedPresentationModel = getPresentationMenuList()

        // When
        val actualModel = menuDomainModel.toPresentation()

        // Then
        Assert.assertEquals(expectedPresentationModel, actualModel)
    }
}