package com.mdevor.littlelemon.domain.repository

import com.mdevor.littlelemon.domain.entity.MenuItem

interface LittleLemonRepository {

    suspend fun getMenu(): List<MenuItem>

    fun getIsLogged(): Boolean

    fun setIsLogged(isLogged: Boolean)
}