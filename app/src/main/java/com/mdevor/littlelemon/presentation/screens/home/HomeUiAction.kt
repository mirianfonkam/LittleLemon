package com.mdevor.littlelemon.presentation.screens.home

sealed class HomeUiAction {
    data class FilterMenu(val filter: String): HomeUiAction()

    data class SearchMenu(val searchQuery: String): HomeUiAction()

    object ClickOnProfile: HomeUiAction()

    object ClearHomeEvent: HomeUiAction()
}
