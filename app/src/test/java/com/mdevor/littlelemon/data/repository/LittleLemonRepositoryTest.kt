package com.mdevor.littlelemon.data.repository

import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSource
import com.mdevor.littlelemon.testhelpers.stubs.getDomainMenuList
import com.mdevor.littlelemon.testhelpers.testrule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class LittleLemonRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val remoteDataSource: MenuRemoteDataSource = mockk(relaxed = true)

    private val repository = LittleLemonRepositoryImpl(
        remoteDataSource = remoteDataSource,
        ioDispatcher = UnconfinedTestDispatcher(),
        localDataSource = mockk(relaxed = true)
    )

    @Test
    fun `GIVEN remoteDataSource succeeds WHEN getMenu THEN get a list of MenuItem`() = runBlocking {
        // GIVEN
        coEvery { remoteDataSource.getMenu() } returns getDomainMenuList()
        val expectedResult = getDomainMenuList()

        // WHEN
        val actualResult = repository.getMenu()

        // THEN
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `GIVEN remoteDataSource fails WHEN getMenu THEN throw exception`() = runBlocking {
        // GIVEN
        coEvery { remoteDataSource.getMenu() } throws IOException()

        // WHEN
        val actualResult = runCatching { remoteDataSource.getMenu() }

        // THEN
        assert(actualResult.isFailure)
    }
}