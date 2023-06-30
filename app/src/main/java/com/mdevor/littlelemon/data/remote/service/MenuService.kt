package com.mdevor.littlelemon.data.remote.service

import com.mdevor.littlelemon.domain.entity.MenuEntity

interface MenuService {

    suspend fun getMenu(): List<MenuEntity>
}