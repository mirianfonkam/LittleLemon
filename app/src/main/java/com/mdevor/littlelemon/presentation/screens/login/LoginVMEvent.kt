package com.mdevor.littlelemon.presentation.screens.login

sealed class LoginVMEvent {
    object NavigateToHome : LoginVMEvent()
}