package com.mdevor.littlelemon.presentation.login

sealed class LoginUiEvent {
    data class UpdateFirstName(val firstName: String) : LoginUiEvent()

    data class UpdateLastName(val lastName: String) : LoginUiEvent()

    data class UpdateEmail(val email: String) : LoginUiEvent()

    object OnRegisterButtonClicked : LoginUiEvent()
}
