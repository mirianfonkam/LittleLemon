package com.mdevor.littlelemon

import androidx.compose.ui.test.junit4.createComposeRule
import com.mdevor.littlelemon.presentation.screens.home.HomeScreenContent
import com.mdevor.littlelemon.presentation.screens.home.HomeUiState
import org.junit.Rule
import org.junit.Test

class HomeScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenEmptyHomeUiState_whenViewOpens_thenAssertEmptyViewsDisplaysAsExpected() {
        // WHEN
        composeTestRule.setContent {
            HomeScreenContent(
                viewState = HomeUiState(),
                viewAction = {}
            )
        }

        // THEN
    }
}