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
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
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
            LittleLemonTheme {
                ProfileScreenContent(
                    viewState = ProfileUiState(),
                    viewAction = {}
                )
            }
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
            InfoData(R.string.first_name, "John"),
            InfoData(R.string.last_name, "Doe"),
            InfoData(R.string.email, "john.doe@example.com")
        )

        // WHEN
        composeTestRule.setContent {
            LittleLemonTheme {
                ProfileScreenContent(
                    viewState = ProfileUiState(userInfoList),
                    viewAction = {}
                )
            }
        }

        // THEN
        with(composeTestRule) {
            assertStaticComposablesAreDisplayed()
            onNodeWithText("First Name").assertIsDisplayed()
            onNodeWithText("Last Name").assertIsDisplayed()
            onNodeWithText("Email").assertIsDisplayed()
            userInfoList.forEach { info ->
                onNodeWithText(info.value).assertIsDisplayed()
            }
        }
    }

    @Test
    fun whenLogoutButtonClicked_thenAssertClickLogoutButtonAction() {
        // GIVEN
        composeTestRule.setContent {
            LittleLemonTheme {
                ProfileScreenContent(
                    viewState = ProfileUiState(),
                    viewAction = mockViewActionMock
                )
            }
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
            LittleLemonTheme {
                ProfileScreenContent(
                    viewState = ProfileUiState(),
                    viewAction = mockViewActionMock
                )
            }
        }

        // WHEN
        composeTestRule.onNodeWithContentDescription("Back button").performClick()

        // THEN
        verify(exactly = 1) { mockViewActionMock(ProfileUiAction.ClickBackButton) }
    }
}