package com.mdevor.littlelemon.data.local.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mdevor.littlelemon.data.local.entity.MenuLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    fun getAll(): Flow<List<MenuLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(menuItems: List<MenuLocalEntity>)
}