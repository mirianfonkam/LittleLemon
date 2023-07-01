package com.mdevor.littlelemon.data.repository

import com.mdevor.littlelemon.data.local.datasource.LittleLemonLocalDataSource
import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSource
import com.mdevor.littlelemon.domain.entity.MenuEntity
import com.mdevor.littlelemon.domain.entity.UserEntity
import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LittleLemonRepositoryImpl(
    private val remoteDataSource: MenuRemoteDataSource,
    private val localDataSource: LittleLemonLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
): LittleLemonRepository {

    override suspend fun getMenu(): List<MenuEntity> {
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

    override fun getUserData(): UserEntity {
        with(localDataSource) {
            return UserEntity(
                firstName = getFirstName(),
                lastName = getLastName(),
                email = getEmail(),
            )
        }
    }
    override fun setUserData(firstName: String?, lastName: String?, email: String?) {
        with(localDataSource) {
            firstName?.let { setFirstName(it) }
            lastName?.let { setLastName(it) }
            email?.let { setEmail(it) }
        }
    }
}