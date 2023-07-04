package com.mdevor.littlelemon.data.mapper

import com.mdevor.littlelemon.testhelpers.stubs.getLocalDataMenuList
import com.mdevor.littlelemon.testhelpers.stubs.getRemoteDataMenuList
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteToLocalDataMapperTest {

    @Test
    fun `GIVEN remote data model WHEN toLocalDataModel is called THEN should return expected local data model`() {
        // Given
        val remoteDataModel = getRemoteDataMenuList()
        val expectedLocalDataModel = getLocalDataMenuList()

        // When
        val actualModel = remoteDataModel.toLocalDataModel()

        // Then
        assertEquals(expectedLocalDataModel, actualModel)
    }
}