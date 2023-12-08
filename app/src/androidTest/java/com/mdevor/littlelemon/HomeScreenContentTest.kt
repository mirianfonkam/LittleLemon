package com.mdevor.littlelemon

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.mdevor.littlelemon.presentation.screens.login.LoginScreenContent
import com.mdevor.littlelemon.presentation.screens.login.LoginUiAction
import com.mdevor.littlelemon.presentation.screens.login.LoginUiState
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class LoginScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val mockViewActionMock: (LoginUiAction) -> Unit = mockk(relaxed = true)

    @Test
    fun givenEmptyLoginUiState_whenViewOpens_thenAssertEmptyViewsDisplaysAsExpected() {
        // WHEN
        composeTestRule.setContent {
            LoginScreenContent(
                viewState = LoginUiState(),
                viewAction = {}
            )
        }

        // THEN
        composeTestRule
            .onNodeWithText("Let's get to know you")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("First Name")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Last Name")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Email")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("Enter Your First Name")
            .assertTextEquals("")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Enter Your Last Name")
            .assertTextEquals("")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Enter Your Email Address")
            .assertTextEquals("")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Register")
            .assertIsDisplayed()
    }

    @Test
    fun givenEmptyLoginUiState_whenRegisterBtnClicked_thenAssertClickRegisterButtonAction() {
        // GIVEN
        composeTestRule.setContent {
            LoginScreenContent(
                viewState = LoginUiState(),
                viewAction = mockViewActionMock
            )
        }

        // WHEN
        composeTestRule.onNodeWithText("Register")
            .performClick()

        // THEN
        verify(exactly = 1) { mockViewActionMock(LoginUiAction.ClickRegisterButton) }
    }

    @Test
    fun givenUiStateWithNonEmptyLoginStatusMessage_whenLogin_thenAssertHideLoginStatusMessageAction() {
        // GIVEN & WHEN
        composeTestRule.setContent {
            LoginScreenContent(
                viewState = LoginUiState(
                    loginStatusMessage = "Registration unsuccessful. Please enter all the data."
                ),
                viewAction = mockViewActionMock
            )
        }

        // THEN
        verify(exactly = 1) { mockViewActionMock(LoginUiAction.HideLoginStatusMessage) }
    }

    @Test
    fun givenUiStateWithCompleteUserInfo_whenLogin_thenAssertTextInputHasCorrespondingText() {
        // GIVEN
        val expectedFirstName = "Mirian"
        val expectedLastName = "Fonkam"
        val expectedEmail = "mirianfonkam@github.com"



        // WHEN
        composeTestRule.setContent {
            LoginScreenContent(
                viewState = LoginUiState(
                    firstName = expectedFirstName,
                    lastName = expectedLastName,
                    email = expectedEmail,
                ),
                viewAction = {}
            )
        }

        // THEN
        composeTestRule
            .onNodeWithContentDescription("Enter Your First Name")
            .assertTextEquals(expectedFirstName)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Enter Your Last Name")
            .assertTextEquals(expectedLastName)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Enter Your Email Address")
            .assertTextEquals(expectedEmail)
            .assertIsDisplayed()
    }

    @Test
    fun whenTypeFirstName_thenAssertUpdateFirstNameAction() {
        // GIVEN
        composeTestRule.setContent {
            LoginScreenContent(
                viewState = LoginUiState(),
                viewAction = mockViewActionMock
            )
        }

        // WHEN
        composeTestRule.onNodeWithContentDescription("Enter Your First Name")
            .performTextInput("Kotlin")

        // THEN
        verify { mockViewActionMock(LoginUiAction.UpdateFirstName(firstName = "Kotlin")) }
    }

    @Test
    fun whenTypeLastName_thenAssertUpdateLastNameAction() {
        // GIVEN
        composeTestRule.setContent {
            LoginScreenContent(
                viewState = LoginUiState(),
                viewAction = mockViewActionMock
            )
        }

        // WHEN
        composeTestRule.onNodeWithContentDescription("Enter Your Last Name")
            .performTextInput("Jetbrains")

        // THEN
        verify { mockViewActionMock(LoginUiAction.UpdateLastName(lastName = "Jetbrains")) }
    }

    @Test
    fun whenTypeEmail_thenAssertUpdateEmailAction() {
        // GIVEN
        composeTestRule.setContent {
            LoginScreenContent(
                viewState = LoginUiState(),
                viewAction = mockViewActionMock
            )
        }

        // WHEN
        composeTestRule.onNodeWithContentDescription("Enter Your Email Address")
            .performTextInput("kotlin.jetbrains@google.com")

        // THEN
        verify { mockViewActionMock(LoginUiAction.UpdateEmail(email = "kotlin.jetbrains@google.com")) }
    }
}