package com.mdevor.littlelemon.data.remote.datasource

import com.mdevor.littlelemon.data.remote.model.MenuItemRequest

interface MenuRemoteDataSource {

    suspend fun getMenu(): List<MenuItemRequest>
}