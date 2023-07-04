package com.mdevor.littlelemon.data.local.datasource

import com.mdevor.littlelemon.data.local.entity.MenuLocalEntity

interface LittleLemonLocalDataSource {
    fun getFirstName(): String
    fun getLastName(): String
    fun getEmail(): String
    fun setFirstName(firstName: String)
    fun setLastName(lastName: String)
    fun setEmail(email: String)
    fun getIsLogged(): Boolean
    fun setIsLogged(isLogged: Boolean)
    suspend fun getMenu(): List<MenuLocalEntity>
    suspend fun insertMenu(menuItems: List<MenuLocalEntity>)
}