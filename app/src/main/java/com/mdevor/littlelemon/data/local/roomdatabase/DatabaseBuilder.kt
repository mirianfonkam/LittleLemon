package com.mdevor.littlelemon.data.local.roomdatabase

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    fun build(context: Context): LittleLemonRoomDatabase {
        return Room.databaseBuilder(
            context,
            LittleLemonRoomDatabase::class.java,
            "littlelemon_database"
        ).build()
    }
}