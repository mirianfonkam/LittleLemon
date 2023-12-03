package com.mdevor.littlelemon.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mdevor.littlelemon.data.local.entity.MenuLocalEntity
import com.mdevor.littlelemon.data.local.roomdatabase.LittleLemonRoomDatabase
import com.mdevor.littlelemon.data.local.roomdatabase.MenuDao
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MenuDatabaseTest {

    private lateinit var menuDao: MenuDao
    private lateinit var db: LittleLemonRoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, LittleLemonRoomDatabase::class.java).build()
        menuDao = db.getMenuDao()
    }

    @Test
    fun givenNoItemsInserted_whenGetAll_thenAssertEmptyListIsReturned() = runTest {
        // GIVEN
        val expectedList = emptyList<MenuLocalEntity>()

        // WHEN
        val actualList = menuDao.getAll()

        // THEN
        assertEquals(expectedList, actualList)
    }

    @Test
    fun givenMenuListIsInserted_whenGetAll_thenAssertExpectedMenuListIsReturned() = runTest {
        // GIVEN
        val expectedList = listOf(
            MenuLocalEntity(
                title = "Greek Salad",
                description = "Our delicious salad is served with Feta cheese and peeled cucumber. Includes tomatoes, onions, olives, salt and oregano in the ingredients.",
                image = "imageURL",
                category = "starters",
                price = 12.99,
            ),
            MenuLocalEntity(
                title = "Pasta",
                description = "Delicious pasta for your delight.",
                image = "imageURL",
                category = "mains",
                price = 6.99,
            )
        )
        menuDao.insertAll(menuItems = expectedList)

        // WHEN
        val actualList = menuDao.getAll()

        // THEN
        assertEquals(expectedList, actualList)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}