package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.repository.LittleLemonRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetIsLoggedUseCaseTest {

    private val repository = mockk<LittleLemonRepository>(relaxed = true)
    private val getIsLoggedUseCase = GetIsLoggedUseCase(repository = repository)

    @Test
    fun `GIVEN repository getIsLogged returns value WHEN invoke THEN assert it returns matching value`() {
        // GIVEN
        val expectedIsLoggedValues = listOf(true, false)
        expectedIsLoggedValues.forEach { isLoggedExpected ->
            every { repository.getIsLogged() } returns isLoggedExpected

        // WHEN
            val actualResult = getIsLoggedUseCase()

        // THEN
            assertEquals(isLoggedExpected, actualResult)
        }
    }

    @Test
    fun `GIVEN repository getIsLogged throws exception WHEN invoke THEN assert that exception propagates`() {
        // GIVEN
        every { repository.getIsLogged() } throws ClassCastException()

        // WHEN & THEN
        assertFailsWith<ClassCastException> {
            getIsLoggedUseCase()
        }
    }
}