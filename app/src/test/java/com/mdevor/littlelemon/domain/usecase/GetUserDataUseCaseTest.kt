package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.entity.UserEntity
import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetUserDataUseCaseTest {

    private val repository = mockk<LittleLemonRepository>(relaxed = true)
    private val getUserDataUseCase = GetUserDataUseCase(repository = repository)

    @Test
    fun `GIVEN repository getUserData returns user data WHEN invoke THEN assert it returns matching user data`() {
        // GIVEN
        val expectedUserData = UserEntity(
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com"
        )
        every { repository.getUserData() } returns expectedUserData

        // WHEN
        val actualResult = getUserDataUseCase()

        // THEN
        assertEquals(expectedUserData, actualResult)
    }

    @Test
    fun `GIVEN repository getUserData returns user with empty values WHEN invoke THEN assert it returns empty UserEntity`() {
        // GIVEN
        val expectedUserData = UserEntity(
            firstName = "",
            lastName = "" ,
            email = ""
        )
        every { repository.getUserData() } returns expectedUserData

        // WHEN
        val actualResult = getUserDataUseCase()

        // THEN
        assertEquals(expectedUserData, actualResult)
    }

    @Test
    fun `GIVEN repository getUserData throws exception WHEN invoke THEN assert it throws the same exception`() {
        // GIVEN
        val exception = RuntimeException("Error retrieving user data")
        every { repository.getUserData() } throws exception

        // WHEN & THEN
        assertFailsWith<RuntimeException> {
            getUserDataUseCase()
        }
    }
}