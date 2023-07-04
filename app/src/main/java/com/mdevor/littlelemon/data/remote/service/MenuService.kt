package com.mdevor.littlelemon.data.remote.service

import com.mdevor.littlelemon.data.remote.model.MenuItemRequest

interface MenuService {

    suspend fun getMenu(): List<MenuItemRequest>
}