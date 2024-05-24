package com.mdevor.littlelemon.presentation.screens.login

import androidx.annotation.StringRes

data class LoginUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    @StringRes val loginStatusMessage: Int? = null,
    val loginEvent: LoginVMEvent? = null,
)