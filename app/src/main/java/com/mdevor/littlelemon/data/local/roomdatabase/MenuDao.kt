package com.mdevor.littlelemon.data.local.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdevor.littlelemon.data.local.entity.MenuLocalEntity

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    suspend fun getAll(): List<MenuLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menuItems: List<MenuLocalEntity>)
}