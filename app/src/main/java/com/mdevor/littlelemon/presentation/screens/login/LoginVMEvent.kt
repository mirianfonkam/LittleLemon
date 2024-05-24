package com.mdevor.littlelemon.presentation.screens.login

sealed class LoginVMEvent {
    data object NavigateToHome : LoginVMEvent()
}