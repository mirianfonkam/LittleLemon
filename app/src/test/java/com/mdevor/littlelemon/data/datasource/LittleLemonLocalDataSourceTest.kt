package com.mdevor.littlelemon.data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mdevor.littlelemon.data.local.datasource.LittleLemonLocalDataSourceImpl
import com.mdevor.littlelemon.data.local.roomdatabase.MenuDao
import com.mdevor.littlelemon.testhelpers.stubs.getLocalDataMenuList
import com.mdevor.littlelemon.testhelpers.testrule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LittleLemonLocalDataSourceTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val prefs = mockk<SharedPreferences>(relaxed = true)
    private val db = mockk<MenuDao>(relaxed = true)
    private val dataSource = LittleLemonLocalDataSourceImpl(
        prefs = prefs,
        db = db,
    )

    @Test
    fun `GIVEN prefs first_name returns value WHEN getFirstName THEN assert expected value is returned`() {
        // GIVEN
        val expectedValue = "Ada"
        every { prefs.getString("first_name", "") } returns expectedValue

        // WHEN
        val result = dataSource.getFirstName()

        // THEN
        assertEquals(expectedValue, result)
    }

    @Test
    fun `GIVEN prefs last_name returns value WHEN getLastName THEN assert expected value is returned`() {
        // GIVEN
        val expectedValue = "Lovelace"
        every { prefs.getString("last_name", "") } returns expectedValue

        // WHEN
        val result = dataSource.getLastName()

        // THEN
        assertEquals(expectedValue, result)
    }

    @Test
    fun `GIVEN prefs email returns value WHEN getEmail THEN assert expected value is returned`() {
        // GIVEN
        val expectedValue = "ada.lovelace@gmail.com"
        every { prefs.getString("email", "") } returns expectedValue

        // WHEN
        val result = dataSource.getEmail()

        // THEN
        assertEquals(expectedValue, result)
    }


    @Test
    fun `GIVEN prefs is_logged returns value WHEN getIsLogged THEN assert expected value is returned`() {
        // GIVEN
        val expectedValue = true
        every { prefs.getBoolean("is_logged", false) } returns expectedValue

        // WHEN
        val result = dataSource.getIsLogged()

        // THEN
        assertEquals(expectedValue, result)
    }

    @Test
    fun `GIVEN firstName WHEN setFirstName THEN assert prefs is edited with with firstName  `() {
        // GIVEN
        val expectedValue = "Tom"

        // WHEN
        dataSource.setFirstName(expectedValue)

        // THEN
        verify(exactly = 1) { prefs.edit(commit = true) { putString("first_name", expectedValue) } }
    }

    @Test
    fun `GIVEN lastName WHEN setLastName THEN assert prefs is edited with with lastName`() {
        // GIVEN
        val expectedValue = "Hanks"

        // WHEN
        dataSource.setLastName(expectedValue)

        // THEN
        verify(exactly = 1) { prefs.edit(commit = true) { putString("last_name", expectedValue) } }
    }

    @Test
    fun `GIVEN email WHEN setEmail THEN assert prefs is edited with with email`() {
        // GIVEN
        val expectedValue = "tom.hanks@gmail.com"

        // WHEN
        dataSource.setEmail(expectedValue)

        // THEN
        verify(exactly = 1) { prefs.edit(commit = true) { putString("email", expectedValue) } }
    }

    @Test
    fun `GIVEN isLogged WHEN setIsLogged THEN assert prefs is edited with with isLogged`() {
        // GIVEN
        val expectedValue = false

        // WHEN
        dataSource.setIsLogged(expectedValue)

        // THEN
        verify(exactly = 1) { prefs.edit(commit = true) { putBoolean("is_logged", expectedValue) } }
    }

    @Test
    fun `GIVEN db has list of menu entities WHEN getMenu THEN assert the list is returned`() = runTest {
        // GIVEN
        val expectedList = getLocalDataMenuList()
        coEvery { db.getAll() } returns expectedList

        // WHEN
        val actualList = dataSource.getMenu()

        // THEN
        assertEquals(expectedList, actualList)
    }

    @Test
    fun `GIVEN menuItems WHEN insertMenu THEN assert the list is returned`() = runTest {
        // GIVEN
        val expectedList = getLocalDataMenuList()

        // WHEN
        dataSource.insertMenu(expectedList)

        // THEN
        coEvery { db.insertAll(expectedList) }
    }
}