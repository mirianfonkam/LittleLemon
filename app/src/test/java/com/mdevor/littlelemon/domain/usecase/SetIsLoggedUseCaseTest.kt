package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SetIsLoggedUseCaseTest {

    private val repository = mockk<LittleLemonRepository>(relaxed = true)
    private val setIsLoggedUseCase = SetIsLoggedUseCase(repository = repository)

    @Test
    fun `GIVEN isLogged value WHEN invoke THEN verify repository setIsLogged is set with the same value`() {
        // GIVEN
        val expectedIsLoggedValues = listOf(true, false)

        expectedIsLoggedValues.forEach { isLogged ->
        // WHEN
            setIsLoggedUseCase(isLogged = isLogged)

        // THEN
            verify(exactly = 1) { repository.setIsLogged(isLogged = isLogged) }
        }
    }
}