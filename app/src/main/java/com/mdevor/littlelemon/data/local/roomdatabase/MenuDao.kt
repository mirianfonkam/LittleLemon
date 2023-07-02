package com.mdevor.littlelemon.data.local.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mdevor.littlelemon.data.local.entity.MenuLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    fun getAll(): Flow<List<MenuLocalEntity>>

    @Insert
    fun insertAll(vararg menuItems: MenuLocalEntity)

    @Query("SELECT (SELECT COUNT(*) FROM menu) == 0")
    fun isEmpty(): Boolean
}