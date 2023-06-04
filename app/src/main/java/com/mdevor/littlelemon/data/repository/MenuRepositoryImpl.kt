package com.mdevor.littlelemon.data.repository

import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSource
import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.domain.repository.MenuRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MenuRepositoryImpl(
    private val remoteDataSource: MenuRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
): MenuRepository {

    override suspend fun getMenu(): List<MenuItem> {
       return withContext(ioDispatcher) {
            remoteDataSource.getMenu()
        }
    }
}