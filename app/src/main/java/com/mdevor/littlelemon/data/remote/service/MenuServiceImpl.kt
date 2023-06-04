package com.mdevor.littlelemon.data.remote.service

import com.mdevor.littlelemon.data.mapper.toDomain
import com.mdevor.littlelemon.data.remote.model.MenuListRequest
import com.mdevor.littlelemon.domain.entity.MenuItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val URL_STRING = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/menu.json"

class MenuServiceImpl(
    private val httpClient: HttpClient,
): MenuService {

    override suspend fun getMenu(): List<MenuItem> {
        return httpClient.get(urlString = URL_STRING)
            .body<MenuListRequest>().toDomain()
    }
}