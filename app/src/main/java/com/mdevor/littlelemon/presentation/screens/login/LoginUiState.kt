package com.mdevor.littlelemon.presentation.screens.login

data class LoginUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val loginStatusMessage: String = "",
    val loginEvent: LoginVMEvent? = null,
)