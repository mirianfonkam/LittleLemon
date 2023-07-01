package com.mdevor.littlelemon.data.local.sharedpref

import android.content.Context
import android.content.SharedPreferences


class LittleLemonSharedPrefs {

    fun setup(context: Context): SharedPreferences {
        return context.getSharedPreferences("littlelemon_shared_prefs", Context.MODE_PRIVATE)
    }
}