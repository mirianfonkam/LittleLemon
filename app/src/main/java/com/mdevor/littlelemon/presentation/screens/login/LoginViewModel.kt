package com.mdevor.littlelemon.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.domain.usecase.SetIsLoggedUseCase
import com.mdevor.littlelemon.domain.usecase.SetUserDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val setIsLoggedUseCase: SetIsLoggedUseCase,
    private val setUserDataUseCase: SetUserDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun handleViewAction(viewAction: LoginUiAction) {
        when (viewAction) {
            is LoginUiAction.UpdateFirstName -> handleFirstNameUpdate(firstName = viewAction.firstName)
            is LoginUiAction.UpdateLastName -> handleLastNameUpdate(lastName = viewAction.lastName)
            is LoginUiAction.UpdateEmail -> handleEmailUpdate(email = viewAction.email)
            is LoginUiAction.ClickRegisterButton -> handleRegisterButtonClick()
            is LoginUiAction.HideLoginStatusMessage -> handleHideLoginStatusMessage()
        }
    }

    private fun handleHideLoginStatusMessage() {
        _uiState.update { it.copy(loginStatusMessage = null) }
    }

    private fun handleRegisterButtonClick() {
        val hasAllRequiredInfo: Boolean = _uiState.value.run {
            firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
        }

        if (hasAllRequiredInfo) {
            setIsLoggedUseCase(isLogged = true)
            _uiState.update {
                it.copy(
                    loginEvent = LoginVMEvent.NavigateToHome
                )
            }
            with(_uiState.value) {
                setUserDataUseCase(firstName, lastName, email)
            }
        } else {
            _uiState.update {
                it.copy(
                    loginStatusMessage = R.string.registration_unsuccessful_message
                )
            }
        }
    }

    private fun handleEmailUpdate(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    private fun handleLastNameUpdate(lastName: String) {
        _uiState.update { it.copy(lastName = lastName) }
    }

    private fun handleFirstNameUpdate(firstName: String) {
        _uiState.update { it.copy(firstName = firstName) }
    }
}