package com.mdevor.littlelemon

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.mdevor.littlelemon.presentation.model.InfoData
import com.mdevor.littlelemon.presentation.screens.profile.ProfileScreenContent
import com.mdevor.littlelemon.presentation.screens.profile.ProfileUiAction
import com.mdevor.littlelemon.presentation.screens.profile.ProfileUiState
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class ProfileScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewActionMock: (ProfileUiAction) -> Unit = mockk(relaxed = true)

    private fun ComposeContentTestRule.assertStaticComposablesAreDisplayed(): SemanticsNodeInteraction {
        onNodeWithContentDescription("Back button").assertIsDisplayed()
        onNodeWithContentDescription("Your profile picture")
            .assertIsDisplayed()
        return onNodeWithText("Log out").assertIsDisplayed()
    }

    @Test
    fun givenEmptyProfileUiState_whenViewOpens_thenAssertEmptyViewsDisplayAsExpected() {
        // WHEN
        composeTestRule.setContent {
            ProfileScreenContent(
                viewState = ProfileUiState(),
                viewAction = {}
            )
        }

        // THEN
        with(composeTestRule) {
            assertStaticComposablesAreDisplayed()
        }
    }

    @Test
    fun givenNonEmptyProfileUiState_whenViewOpens_thenAssertDisplayedUserInfo() {
        // GIVEN
        val userInfoList = listOf(
            InfoData("First Name", "John"),
            InfoData("Last Name", "Doe"),
            InfoData("Email", "john.doe@example.com")
        )

        // WHEN
        composeTestRule.setContent {
            ProfileScreenContent(
                viewState = ProfileUiState(userInfoList),
                viewAction = {}
            )
        }

        // THEN
        with(composeTestRule) {
            assertStaticComposablesAreDisplayed()
            userInfoList.forEach { info ->
                onNodeWithText(info.label).assertIsDisplayed()
                onNodeWithText(info.value).assertIsDisplayed()
            }
        }
    }

    @Test
    fun whenLogoutButtonClicked_thenAssertClickLogoutButtonAction() {
        // GIVEN
        composeTestRule.setContent {
            ProfileScreenContent(
                viewState = ProfileUiState(),
                viewAction = mockViewActionMock
            )
        }

        // WHEN
        composeTestRule.onNodeWithText("Log out").performClick()

        // THEN
        verify(exactly = 1) { mockViewActionMock(ProfileUiAction.ClickLogoutButton) }
    }

    @Test
    fun whenBackButtonClicked_thenAssertClickBackButtonAction() {
        // GIVEN
        composeTestRule.setContent {
            ProfileScreenContent(
                viewState = ProfileUiState(),
                viewAction = mockViewActionMock
            )
        }

        // WHEN
        composeTestRule.onNodeWithContentDescription("Back button").performClick()

        // THEN
        verify(exactly = 1) { mockViewActionMock(ProfileUiAction.ClickBackButton) }
    }
}