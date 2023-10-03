package com.mdevor.littlelemon.data.datasource

import android.content.SharedPreferences
import com.mdevor.littlelemon.data.local.datasource.LittleLemonLocalDataSourceImpl
import com.mdevor.littlelemon.data.local.roomdatabase.MenuDao
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LittleLemonLocalDataSourceTest {

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
}