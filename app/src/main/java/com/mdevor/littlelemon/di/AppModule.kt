package com.mdevor.littlelemon.di

import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSource
import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSourceImpl
import com.mdevor.littlelemon.data.remote.service.KtorClient
import com.mdevor.littlelemon.data.remote.service.MenuService
import com.mdevor.littlelemon.data.remote.service.MenuServiceImpl
import com.mdevor.littlelemon.data.repository.MenuRepositoryImpl
import com.mdevor.littlelemon.domain.repository.MenuRepository
import com.mdevor.littlelemon.domain.usecase.GetCategoriesUseCase
import com.mdevor.littlelemon.domain.usecase.GetMenuUseCase
import com.mdevor.littlelemon.presentation.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<MenuService> {
        MenuServiceImpl(
            httpClient = KtorClient().build()
        )
    }

    factory<MenuRemoteDataSource> { MenuRemoteDataSourceImpl(service = get()) }

    factory<MenuRepository> {
        MenuRepositoryImpl(
            remoteDataSource = get(),
            ioDispatcher = Dispatchers.IO,
        )
    }

    factory { GetMenuUseCase(get()) }

    factory { GetCategoriesUseCase() }

    viewModel {
        HomeViewModel(
            getMenuUseCase = get(),
            getCategoriesUseCase = get(),
        )
    }
}