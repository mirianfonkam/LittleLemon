package com.mdevor.littlelemon.data.remote.datasource

import com.mdevor.littlelemon.data.remote.service.MenuService
import com.mdevor.littlelemon.domain.entity.MenuItem

class MenuRemoteDataSourceImpl(private val service: MenuService): MenuRemoteDataSource {

    override suspend fun getMenu(): List<MenuItem> {
        return service.getMenu()
    }
}