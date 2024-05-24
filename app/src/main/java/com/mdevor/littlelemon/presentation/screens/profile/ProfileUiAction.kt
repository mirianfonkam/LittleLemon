package com.mdevor.littlelemon.presentation.screens.profile

sealed class ProfileUiAction {

    data object ClickBackButton : ProfileUiAction()

    data object ClickLogoutButton : ProfileUiAction()
}