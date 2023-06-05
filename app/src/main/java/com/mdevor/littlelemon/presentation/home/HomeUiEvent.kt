package com.mdevor.littlelemon.presentation.home

sealed class HomeUiEvent {
    data class FilterMenu(val filter: String): HomeUiEvent()

    data class SearchMenu(val searchQuery: String): HomeUiEvent()
}
