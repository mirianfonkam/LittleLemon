package com.mdevor.littlelemon.data.local.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.mdevor.littlelemon.data.local.entity.MenuLocalEntity
import com.mdevor.littlelemon.data.local.roomdatabase.MenuDao

private const val FIRST_NAME_KEY = "first_name"
private const val LAST_NAME_KEY = "last_name"
private const val EMAIL_KEY = "email"
private const val IS_LOGGED_KEY = "is_logged"

class LittleLemonLocalDataSourceImpl(
    private val prefs: SharedPreferences,
    private val db: MenuDao,
) : LittleLemonLocalDataSource {

    override fun getFirstName(): String = getStringValueByKey(FIRST_NAME_KEY)

    override fun getLastName(): String = getStringValueByKey(LAST_NAME_KEY)

    override fun getEmail(): String = getStringValueByKey(EMAIL_KEY)

    override fun getIsLogged(): Boolean {
        return prefs.getBoolean(IS_LOGGED_KEY, false)
    }

    override fun setFirstName(firstName: String) {
        prefs.edit(commit = true) { putString(FIRST_NAME_KEY, firstName) }
    }

    override fun setLastName(lastName: String) {
        prefs.edit(commit = true) { putString(LAST_NAME_KEY, lastName) }
    }

    override fun setEmail(email: String) {
        prefs.edit(commit = true) { putString(EMAIL_KEY, email) }
    }

    override fun setIsLogged(isLogged: Boolean) {
        prefs.edit(commit = true) { putBoolean(IS_LOGGED_KEY, isLogged) }
    }

    override suspend fun getMenu(): List<MenuLocalEntity> = db.getAll()

    override suspend fun insertMenu(menuItems: List<MenuLocalEntity>) = db.insertAll(menuItems)

    private fun getStringValueByKey(key: String): String {
        return prefs.getString(key, "").orEmpty()
    }
}