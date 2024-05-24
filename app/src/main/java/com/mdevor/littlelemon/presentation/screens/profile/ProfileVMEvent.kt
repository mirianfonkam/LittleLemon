package com.mdevor.littlelemon.presentation.screens.profile

sealed class ProfileVMEvent {
    data object NavigateBack : ProfileVMEvent()
    data object NavigateToLogin : ProfileVMEvent()
}