package com.mdevor.littlelemon.data.local.sharedpref

import android.content.Context
import android.content.SharedPreferences


object LittleLemonSharedPrefs {

    fun build(context: Context): SharedPreferences {
        return context.getSharedPreferences("littlelemon_shared_prefs", Context.MODE_PRIVATE)
    }
}