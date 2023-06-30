package com.mdevor.littlelemon.data.remote.datasource

import com.mdevor.littlelemon.domain.entity.MenuEntity

interface MenuRemoteDataSource {

    suspend fun getMenu(): List<MenuEntity>
}