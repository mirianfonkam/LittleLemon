package com.mdevor.littlelemon.data.mapper

import com.mdevor.littlelemon.testhelpers.stubs.getDomainMenuList
import com.mdevor.littlelemon.testhelpers.stubs.getLocalDataMenuList
import org.junit.Assert.assertEquals
import org.junit.Test

class DataToDomainMapperTest {

    @Test
    fun `GIVEN data model WHEN toDomain is called THEN should return expected domain model`() {
        // Given
        val menuDataModel = getLocalDataMenuList()
        val expectedDomainModel = getDomainMenuList()

        // When
        val actualModel = menuDataModel.toDomain()

        // Then
        assertEquals(expectedDomainModel, actualModel)
    }
}