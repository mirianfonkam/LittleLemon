package com.mdevor.littlelemon.di

import com.mdevor.littlelemon.data.local.LittleLemonLocalDataSource
import com.mdevor.littlelemon.data.local.LittleLemonLocalDataSourceImpl
import com.mdevor.littlelemon.data.local.LittleLemonSharedPrefs
import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSource
import com.mdevor.littlelemon.data.remote.datasource.MenuRemoteDataSourceImpl
import com.mdevor.littlelemon.data.remote.service.KtorClient
import com.mdevor.littlelemon.data.remote.service.MenuService
import com.mdevor.littlelemon.data.remote.service.MenuServiceImpl
import com.mdevor.littlelemon.data.repository.LittleLemonRepositoryImpl
import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import com.mdevor.littlelemon.domain.usecase.GetCategoriesUseCase
import com.mdevor.littlelemon.domain.usecase.GetIsLoggedUseCase
import com.mdevor.littlelemon.domain.usecase.GetMenuUseCase
import com.mdevor.littlelemon.domain.usecase.SetIsLoggedUseCase
import com.mdevor.littlelemon.presentation.navigation.StartNavigationViewModel
import com.mdevor.littlelemon.presentation.screens.home.HomeViewModel
import com.mdevor.littlelemon.presentation.screens.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<MenuService> {
        MenuServiceImpl(
            httpClient = KtorClient().build()
        )
    }

    single<LittleLemonLocalDataSource> {
        LittleLemonLocalDataSourceImpl(
            prefs = LittleLemonSharedPrefs().setup(context = get())
        )
    }

    factory<MenuRemoteDataSource> { MenuRemoteDataSourceImpl(service = get()) }

    factory<LittleLemonRepository> {
        LittleLemonRepositoryImpl(
            remoteDataSource = get(),
            ioDispatcher = Dispatchers.IO,
            localDataSource = get()
        )
    }

    factory { GetMenuUseCase(get()) }

    factory { GetCategoriesUseCase() }

    factory { SetIsLoggedUseCase(get()) }

    factory { GetIsLoggedUseCase(get()) }

    viewModel {
        HomeViewModel(
            getMenuUseCase = get(),
            getCategoriesUseCase = get(),
        )
    }

    viewModel {
        LoginViewModel(
            setIsLoggedUseCase = get()
        )
    }

    viewModel {
        StartNavigationViewModel(
            getIsLoggedUseCase = get(),
        )
    }
}