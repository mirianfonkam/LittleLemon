package com.mdevor.littlelemon.data.remote.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    fun build(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = true
                    },
                    contentType = ContentType("text", "plain")
                )
            }
        }
    }
}