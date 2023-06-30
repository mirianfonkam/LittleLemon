package com.mdevor.littlelemon.presentation.screens.login

sealed class LoginUiAction {
    data class UpdateFirstName(val firstName: String) : LoginUiAction()

    data class UpdateLastName(val lastName: String) : LoginUiAction()

    data class UpdateEmail(val email: String) : LoginUiAction()

    object ClickRegisterButton : LoginUiAction()

    object HideLoginStatusMessage : LoginUiAction()
}
