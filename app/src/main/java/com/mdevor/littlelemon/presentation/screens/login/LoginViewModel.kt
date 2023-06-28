package com.mdevor.littlelemon.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.mdevor.littlelemon.domain.usecase.SetIsLoggedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(private val setIsLoggedUseCase: SetIsLoggedUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun handleViewAction(viewAction: LoginUiAction) {
        when (viewAction) {
            is LoginUiAction.UpdateFirstName -> handleFirstNameUpdate(firstName = viewAction.firstName)
            is LoginUiAction.UpdateLastName -> handleLastNameUpdate(lastName = viewAction.lastName)
            is LoginUiAction.UpdateEmail -> handleEmailUpdate(email = viewAction.email)
            is LoginUiAction.OnRegisterButtonClicked -> handleOnRegisterButtonClicked()
            is LoginUiAction.HideLoginStatusMessage -> handleHideLoginStatusMessage()
        }
    }

    private fun handleHideLoginStatusMessage() {
        _uiState.update { it.copy(loginStatusMessage = "") }
    }

    private fun handleOnRegisterButtonClicked() {
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

            // Save user info to shared preferences
        } else {
            _uiState.update {
                it.copy(
                    loginStatusMessage = "Registration unsuccessful. Please enter all the data."
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