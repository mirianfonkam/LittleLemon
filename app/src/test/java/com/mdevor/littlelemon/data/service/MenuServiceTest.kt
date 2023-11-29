package com.mdevor.littlelemon.data.service

import com.mdevor.littlelemon.data.remote.model.MenuItemRequest
import com.mdevor.littlelemon.data.remote.service.MenuServiceImpl
import com.mdevor.littlelemon.testhelpers.mockhelper.mockResponse
import com.mdevor.littlelemon.testhelpers.testrule.MainDispatcherRule
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

private const val FULL_PATH = "/mirianfonkam/LittleLemon/main/littlelemonapi/menu.json"

class MenuServiceTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createMockClient(mockEngine: MockEngine): HttpClient {
        return HttpClient(mockEngine) {
            install(ContentNegotiation) { json() }
        }
    }

    private fun buildEngine(isSuccessfulResponse: Boolean): MockEngine {
        return MockEngine { request ->
            when (request.url.fullPath) {
                FULL_PATH -> {
                    if (isSuccessfulResponse) {
                        respond(
                            content = mockResponse("getMenuSuccessfulResponse.json"),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    } else {
                        respond(
                            content = "{}",
                            status = HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                }
                else -> error("Error ${request.url.fullPath}")
            }
        }
    }

    private fun buildMockService(isSuccessfulResponse: Boolean = true): MenuServiceImpl {
        return MenuServiceImpl(
            httpClient = createMockClient(buildEngine(isSuccessfulResponse = isSuccessfulResponse))
        )
    }

    @Test
    fun `GIVEN httpClient gets menu list WHEN getMenu THEN assert it returns 5 menu items`() = runTest {
        // GIVEN
        val expectedMenuItemSize = 5

        // WHEN
        val actualList = buildMockService().getMenu()

        // THEN
        assertEquals(expectedMenuItemSize, actualList.size)
    }

    @Test
    fun `GIVEN httpClient gets menu list WHEN getMenu THEN assert last item is Lemon Desert menu item`() = runTest {
        // GIVEN
        val expectedDataItem = MenuItemRequest(
            title = "Lemon Desert",
            description = "Traditional homemade Italian Lemon Ricotta Cake.",
            price = 4.99,
            image = "https://www.marcellinaincucina.com/wp-content/uploads/2023/02/italian-lemon-cake-featured.jpg",
            category = "desserts"
        )

        // WHEN
        val lastMenuItem = buildMockService().getMenu().last()

        // THEN
        assertEquals(expectedDataItem, lastMenuItem)
    }

    @Test
    fun `GIVEN httpClient returns error WHEN getMenu THEN assert error is thrown`() = runTest {
        // WHEN
        val actualResult = runCatching { buildMockService(isSuccessfulResponse = false).getMenu() }

        // THEN
        assert(actualResult.isFailure)
    }
}