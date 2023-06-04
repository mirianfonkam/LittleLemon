package com.mdevor.littlelemon.data.remote.service

import com.mdevor.littlelemon.domain.entity.MenuItem

interface MenuService {

    suspend fun getMenu(): List<MenuItem>
}