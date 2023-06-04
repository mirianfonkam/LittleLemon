package com.mdevor.littlelemon.data.remote.datasource

import com.mdevor.littlelemon.domain.entity.MenuItem

interface MenuRemoteDataSource {

    suspend fun getMenu(): List<MenuItem>
}