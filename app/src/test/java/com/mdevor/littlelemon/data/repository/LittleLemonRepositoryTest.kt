package com.mdevor.littlelemon.data.repository

import android.util.Log
import com.mdevor.littlelemon.data.local.datasource.LittleLemonLocalDataSource
import com.mdevor.littlelemon.data.mapper.toLocalDataModel
import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSource
import com.mdevor.littlelemon.domain.entity.MenuEntity
import com.mdevor.littlelemon.domain.entity.UserEntity
import com.mdevor.littlelemon.testhelpers.stubs.getRemoteDataMenuList
import com.mdevor.littlelemon.testhelpers.stubs.getDomainMenuList
import com.mdevor.littlelemon.testhelpers.stubs.getLocalDataMenuList
import com.mdevor.littlelemon.testhelpers.testrule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
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
    private val localDataSource: LittleLemonLocalDataSource = mockk(relaxed = true)

    private val repository = LittleLemonRepositoryImpl(
        remoteDataSource = remoteDataSource,
        ioDispatcher = UnconfinedTestDispatcher(),
        localDataSource = localDataSource
    )

    @Test
    fun `GIVEN remoteDataSource succeeds WHEN getMenu THEN insert data in DB and get data from DB`() = runBlocking {
        // GIVEN
        coEvery { remoteDataSource.getMenu() } returns getRemoteDataMenuList()
        coEvery { localDataSource.getMenu() } returns getLocalDataMenuList()
        val expectedResult = getDomainMenuList()

        // WHEN
        val actualResult = repository.getMenu()

        // THEN
        coVerifySequence {
            remoteDataSource.getMenu()
            localDataSource.insertMenu(getRemoteDataMenuList().toLocalDataModel())
            localDataSource.getMenu()
        }
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `GIVEN remoteDataSource fails WHEN getMenu THEN get a list of MenuItem from DB only`() = runBlocking {
        // GIVEN
        coEvery { remoteDataSource.getMenu() } throws IOException()
        coEvery { localDataSource.getMenu() } returns emptyList()
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0

        val expectedResult = emptyList<MenuEntity>()

        // WHEN
        val actualResult = repository.getMenu()

        // THEN
        coVerify(inverse = true) { localDataSource.insertMenu(getRemoteDataMenuList().toLocalDataModel()) }
        coVerifySequence {
            remoteDataSource.getMenu()
            localDataSource.getMenu()
        }
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `GIVEN localDataSource getIsLogged returns a value WHEN getIsLogged THEN assert same value is returned`() {
        // GIVEN
        val expectedIsLogged = listOf(true, false)

        expectedIsLogged.forEach { isLogged ->
            every { localDataSource.getIsLogged() } returns isLogged

        // WHEN
            val actualIsLogged = repository.getIsLogged()

        // THEN
            assertEquals(isLogged, actualIsLogged)
        }
    }

    @Test
    fun `GIVEN value WHEN setIsLogged THEN assert localDataSource setIsLogged is called with same value`() {
        // GIVEN
        val expectedIsLogged = listOf(true, false)

        expectedIsLogged.forEach { isLogged ->
        // WHEN
            repository.setIsLogged(isLogged)

        // THEN
            verify(exactly = 1) { localDataSource.setIsLogged(isLogged) }
        }
    }

    @Test
    fun `GIVEN localDataSource has all user fields WHEN getUserData THEN assert user data is returned with all fields`() {
        // GIVEN
        val expectedFirstName = "John"
        val expectedLastName = "Doe"
        val expectedEmail = "john.doe@example.com"
        every { localDataSource.getFirstName() } returns expectedFirstName
        every { localDataSource.getLastName() } returns expectedLastName
        every { localDataSource.getEmail() } returns expectedEmail

        // WHEN
        val userData = repository.getUserData()

        // THEN
        assertEquals(expectedFirstName, userData.firstName)
        assertEquals(expectedLastName, userData.lastName)
        assertEquals(expectedEmail, userData.email)
    }

    @Test
    fun `GIVEN localDataSource returns user data with empty fields WHEN getUserData THEN assert user data is returned with empty fields`() {
        // GIVEN
        every { localDataSource.getFirstName() } returns ""
        every { localDataSource.getLastName() } returns ""
        every { localDataSource.getEmail() } returns ""
        val expectedUserData = UserEntity(
            firstName = "",
            lastName = "",
            email = ""
        )

        // WHEN
        val userData = repository.getUserData()

        // THEN
        assertEquals(userData, expectedUserData)
    }

    @Test
    fun `GIVEN firstName, lastName & email WHEN setUserData THEN assert localDataSource is called with corresponding fields`() {
        // GIVEN
        val firstName = "Harry"
        val lastName = "Potter"
        val email = "harry.potter@gmail.com"

        // WHEN
        repository.setUserData(firstName, lastName, email)

        // THEN
        verify { localDataSource.setFirstName(firstName) }
        verify { localDataSource.setLastName(lastName) }
        verify { localDataSource.setEmail(email) }
    }
}