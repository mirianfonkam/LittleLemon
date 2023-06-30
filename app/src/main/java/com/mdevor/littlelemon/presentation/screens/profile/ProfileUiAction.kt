package com.mdevor.littlelemon.presentation.screens.profile

sealed class ProfileUiAction {

    object ClickBackButton : ProfileUiAction()

    object ClickLogoutButton : ProfileUiAction()
}