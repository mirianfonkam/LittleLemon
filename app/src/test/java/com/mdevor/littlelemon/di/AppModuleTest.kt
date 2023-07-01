package com.mdevor.littlelemon.di

import org.junit.Test
import org.koin.android.test.verify.verify
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest

class AppModuleTest: KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules(){
        appModule.verify(
            extraTypes = listOf(
                io.ktor.client.engine.HttpClientEngine::class,
                io.ktor.client.HttpClientConfig::class
            )
        )
    }
}