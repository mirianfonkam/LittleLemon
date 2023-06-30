package com.mdevor.littlelemon.presentation.screens.profile

sealed class ProfileVMEvent {
    object NavigateBack : ProfileVMEvent()
    object Logout : ProfileVMEvent()
}