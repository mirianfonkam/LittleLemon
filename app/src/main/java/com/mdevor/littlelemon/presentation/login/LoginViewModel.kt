package com.mdevor.littlelemon.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun dispatchViewEvent(viewAction: LoginUiEvent) {
        when (viewAction) {
            is LoginUiEvent.UpdateFirstName -> handleFirstNameUpdate(firstName = viewAction.firstName)
            is LoginUiEvent.UpdateLastName -> handleLastNameUpdate(lastName = viewAction.lastName)
            is LoginUiEvent.UpdateEmail -> handleEmailUpdate(email = viewAction.email)
            is LoginUiEvent.OnRegisterButtonClicked -> handleOnRegisterButtonClicked()
        }
    }

    private fun handleOnRegisterButtonClicked() {

    }

    private fun handleEmailUpdate(email: String) {

    }

    private fun handleLastNameUpdate(lastName: String) {

    }

    private fun handleFirstNameUpdate(firstName: String) {

    }
}