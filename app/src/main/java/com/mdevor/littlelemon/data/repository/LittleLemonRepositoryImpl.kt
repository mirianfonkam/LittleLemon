package com.mdevor.littlelemon.data.repository

import com.mdevor.littlelemon.data.local.LittleLemonLocalDataSource
import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSource
import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LittleLemonRepositoryImpl(
    private val remoteDataSource: MenuRemoteDataSource,
    private val localDataSource: LittleLemonLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
): LittleLemonRepository {

    override suspend fun getMenu(): List<MenuItem> {
       return withContext(ioDispatcher) {
            remoteDataSource.getMenu()
        }
    }

    override fun getIsLogged(): Boolean {
        return localDataSource.getIsLogged()
    }

    override fun setIsLogged(isLogged: Boolean) {
        localDataSource.setIsLogged(isLogged)
    }
}