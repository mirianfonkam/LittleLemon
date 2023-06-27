package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import com.mdevor.littlelemon.testhelpers.stubs.getDomainMenuList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.TimeoutException

class GetMenuUseCaseTest {

    private val repository = mockk<LittleLemonRepository>()
    private val getMenuUseCase = GetMenuUseCase(repository = repository)

    @Test
    fun `GIVEN menuRepository returns menuList WHEN invoke THEN assert it returns menuList`() = runBlocking {
        // GIVEN
        coEvery { repository.getMenu() } returns getDomainMenuList()
        val expectedResult = getDomainMenuList()

        // WHEN
        val actualResult = getMenuUseCase()

        // THEN
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `GIVEN menuRepository throws exception WHEN invoke THEN assert it has a failed outcome`() = runBlocking {
        // GIVEN
        coEvery { repository.getMenu() } throws TimeoutException()

        // WHEN
        val actualResult = runCatching { getMenuUseCase() }

        // THEN
        assert(actualResult.isFailure)
    }
}