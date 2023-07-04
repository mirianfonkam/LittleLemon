package com.mdevor.littlelemon.data.remote.service

import com.mdevor.littlelemon.data.remote.model.MenuItemRequest
import com.mdevor.littlelemon.data.remote.model.MenuListRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val URL_STRING = "https://raw.githubusercontent.com/mirianfonkam/LittleLemon/main/littlelemonapi/menu.json"

class MenuServiceImpl(
    private val httpClient: HttpClient,
): MenuService {

    override suspend fun getMenu(): List<MenuItemRequest> {
        return httpClient.get(urlString = URL_STRING).body<MenuListRequest>().menuList
    }
}