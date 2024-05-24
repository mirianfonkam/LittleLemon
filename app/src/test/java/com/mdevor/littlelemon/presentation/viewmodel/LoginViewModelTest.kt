package com.mdevor.littlelemon.presentation.viewmodel

import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.domain.usecase.SetIsLoggedUseCase
import com.mdevor.littlelemon.domain.usecase.SetUserDataUseCase
import com.mdevor.littlelemon.presentation.screens.login.LoginUiAction
import com.mdevor.littlelemon.presentation.screens.login.LoginUiState
import com.mdevor.littlelemon.presentation.screens.login.LoginVMEvent
import com.mdevor.littlelemon.presentation.screens.login.LoginViewModel
import com.mdevor.littlelemon.testhelpers.stubs.getUserEntity
import com.mdevor.littlelemon.testhelpers.testrule.MainDispatcherRule
import io.mockk.Called
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.testng.Assert.assertEquals

class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LoginViewModel
    private val setIsLoggedUseCase = mockk<SetIsLoggedUseCase>(relaxed = true)
    private val setUserDataUseCase = mockk<SetUserDataUseCase>(relaxed = true)

    @Before
    fun setupViewModel() {
        viewModel = LoginViewModel(
            setIsLoggedUseCase = setIsLoggedUseCase,
            setUserDataUseCase = setUserDataUseCase,
        )
    }

    private fun enterAllRequiredInfo() {
        with(viewModel) {
            with(getUserEntity()) {
                handleViewAction(LoginUiAction.UpdateFirstName(firstName))
                handleViewAction(LoginUiAction.UpdateLastName(lastName))
                handleViewAction(LoginUiAction.UpdateEmail(email))
            }
        }
    }

    @Test
    fun `GIVEN firstName WHEN handle UpdateFirstName uiAction THEN assert uiState updates with firstName`() {
        // GIVEN
        val firstName = "Alan"
        val expected = LoginUiState(
            firstName = firstName,
        )

        // WHEN
        viewModel.handleViewAction(LoginUiAction.UpdateFirstName(firstName))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN lastName WHEN handle UpdateLastName uiAction THEN assert uiState updates with lastName`() {
        // GIVEN
        val lastName = "Turing"
        val expected = LoginUiState(
            lastName = lastName,
        )

        // WHEN
        viewModel.handleViewAction(LoginUiAction.UpdateLastName(lastName))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN email WHEN handle UpdateEmail uiAction THEN assert uiState updates with email`() {
        // GIVEN
        val email = "alan.turing@gmail.com"
        val expected = LoginUiState(
            email = email,
        )

        // WHEN
        viewModel.handleViewAction(LoginUiAction.UpdateEmail(email))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN unsuccessful message WHEN handle ClickRegisterButton THEN assert uiState updates with unsuccessful login status message`() {
        // GIVEN
        val unsuccessfulLoginStatusMessage = R.string.registration_unsuccessful_message

        // WHEN
        viewModel.handleViewAction(LoginUiAction.ClickRegisterButton)

        // THEN
        with(viewModel.uiState.value) {
            assert(email.isBlank())
            assert(firstName.isBlank())
            assert(lastName.isBlank())
            assertEquals(unsuccessfulLoginStatusMessage, loginStatusMessage)
        }
        verify { setIsLoggedUseCase wasNot Called }
    }

    @Test
    fun `GIVEN firstName and lastName are entered but email is blank WHEN ClickRegisterButton THEN assert setIsLoggedUseCase is not called`() {
        // GIVEN
        val firstNameInput = "Ada"
        val lastNameInput = "Lovelace"
        viewModel.handleViewAction(LoginUiAction.UpdateFirstName(firstNameInput))
        viewModel.handleViewAction(LoginUiAction.UpdateLastName(lastNameInput))

        // WHEN
        viewModel.handleViewAction(LoginUiAction.ClickRegisterButton)

        // THEN
        with(viewModel.uiState.value) {
            assert(firstName.isNotBlank())
            assert(lastName.isNotBlank())
            assert(email.isBlank())
        }
        verify { setIsLoggedUseCase wasNot Called }
    }

    @Test
    fun `GIVEN lastName is blank but firstName and email are entered WHEN ClickRegisterButton THEN assert setIsLoggedUseCase is not called`() {
        // GIVEN
        val firstNameInput = "Ada"
        val emailInput = "ada.lovelace@gmail.com"
        viewModel.handleViewAction(LoginUiAction.UpdateFirstName(firstNameInput))
        viewModel.handleViewAction(LoginUiAction.UpdateEmail(emailInput))

        // WHEN
        viewModel.handleViewAction(LoginUiAction.ClickRegisterButton)

        // THEN
        with(viewModel.uiState.value) {
            assert(firstName.isNotBlank())
            assert(lastName.isBlank())
            assert(email.isNotBlank())
        }
        verify { setIsLoggedUseCase wasNot Called }
    }

    @Test
    fun `GIVEN firstName is blank but lastName and email are entered WHEN ClickRegisterButton THEN assert setIsLoggedUseCase is not called`() {
        // GIVEN
        val lastNameInput = "Turing"
        val emailInput = "alan.turing@gmail.com"
        viewModel.handleViewAction(LoginUiAction.UpdateLastName(lastNameInput))
        viewModel.handleViewAction(LoginUiAction.UpdateEmail(emailInput))

        // WHEN
        viewModel.handleViewAction(LoginUiAction.ClickRegisterButton)

        // THEN
        with(viewModel.uiState.value) {
            assert(firstName.isBlank())
            assert(lastName.isNotBlank())
            assert(email.isNotBlank())
        }
        verify { setIsLoggedUseCase wasNot Called }
    }

    @Test
    fun `GIVEN firstName, lastName and email are entered WHEN ClickRegisterButton THEN assert setIsLogged and setUserData useCases are invoked`() {
        // GIVEN
        enterAllRequiredInfo()

        // WHEN
        viewModel.handleViewAction(LoginUiAction.ClickRegisterButton)

        // THEN
        with(viewModel.uiState.value) {
            assert(firstName.isNotBlank())
            assert(lastName.isNotBlank())
            assert(email.isNotBlank())
        }
        verifySequence {
            setIsLoggedUseCase(isLogged = true)
            with(getUserEntity()) {
                setUserDataUseCase(firstName = firstName, lastName = lastName, email = email)
            }
        }
    }

    @Test
    fun `GIVEN hasAllRequiredInfo is entered WHEN ClickRegisterButton THEN assert uiState updates with NavigateToHome event`() {
        // GIVEN
        enterAllRequiredInfo()
        val expectedLoginEventState = with(getUserEntity()) {
            LoginUiState(
                firstName = firstName,
                lastName = lastName,
                email = email,
                loginStatusMessage = null,
                loginEvent = LoginVMEvent.NavigateToHome,
            )
        }

        // WHEN
        viewModel.handleViewAction(LoginUiAction.ClickRegisterButton)

        // THEN
        assertEquals(expectedLoginEventState, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN loginStatusMessage is present WHEN handle HideLoginStatusMessage THEN assert uiState updates with cleared loginStatusMessage`() {
        // GIVEN
        viewModel.handleViewAction(LoginUiAction.UpdateFirstName(getUserEntity().firstName))
        val expectedState = LoginUiState(
            firstName = getUserEntity().firstName,
            lastName = "",
            email = "",
            loginStatusMessage = null,
            loginEvent = null,
        )

        // WHEN
        viewModel.handleViewAction(LoginUiAction.HideLoginStatusMessage)

        // THEN
        assertEquals(expectedState, viewModel.uiState.value)
    }
}