package com.mdevor.littlelemon.data.datasource

import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSourceImpl
import com.mdevor.littlelemon.data.remote.service.MenuService
import com.mdevor.littlelemon.testhelpers.stubs.getRemoteDataMenuList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MenuRemoteDataSourceImplTest {

    private val service: MenuService = mockk()
    private val dataSource = MenuRemoteDataSourceImpl(service)

    @Test
    fun `GIVEN service succeeds WHEN getMenu THEN get a list of MenuItem`() = runBlocking {
        // GIVEN
        coEvery { service.getMenu() } returns getRemoteDataMenuList()
        val expectedResult = getRemoteDataMenuList()

        // WHEN
        val actualResult = dataSource.getMenu()

        // THEN
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `GIVEN service fails WHEN getMenu THEN throw exception`() = runBlocking {
        // GIVEN
        coEvery { service.getMenu() } throws Exception()

        // WHEN
        val actualResult = runCatching { dataSource.getMenu() }

        // THEN
        assert(actualResult.isFailure)
    }
}