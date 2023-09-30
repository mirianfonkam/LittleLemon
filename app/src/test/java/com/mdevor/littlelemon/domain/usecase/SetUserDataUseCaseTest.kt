package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SetUserDataUseCaseTest {

    private val repository = mockk<LittleLemonRepository>(relaxed = true)
    private val setUserDataUseCase = SetUserDataUseCase(repository = repository)

    @Test
    fun `GIVEN setUserData is called with all fields WHEN invoke THEN assert all fields are set in repository`() {
        // GIVEN
        val firstName = "John"
        val lastName = "Doe"
        val email = "john.doe@example.com"

        // WHEN
        setUserDataUseCase(firstName = firstName, lastName = lastName, email = email)

        // THEN
        verify(exactly = 1) { repository.setUserData(firstName, lastName, email) }
    }

    @Test
    fun `GIVEN setUserData is called with one field WHEN invoke THEN assert only that field is set in repository`() {
        // GIVEN
        val firstName = "John"

        // WHEN
        setUserDataUseCase(firstName = firstName)

        // THEN
        verify(exactly = 1) { repository.setUserData(firstName = firstName, lastName = null, email = null) }
    }

    @Test
    fun `GIVEN setUserData is called with no fields WHEN invoke THEN assert all fields are null`() {
        // WHEN
        setUserDataUseCase()

        // THEN
        verify { repository.setUserData(null, null, null) }
    }
}