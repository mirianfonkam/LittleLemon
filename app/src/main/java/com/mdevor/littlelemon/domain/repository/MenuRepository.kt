package com.mdevor.littlelemon.domain.repository

import com.mdevor.littlelemon.domain.entity.MenuItem

interface MenuRepository {

    suspend fun getMenu(): List<MenuItem>
}