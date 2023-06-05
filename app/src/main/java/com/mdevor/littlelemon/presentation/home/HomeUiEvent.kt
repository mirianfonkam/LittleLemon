package com.mdevor.littlelemon.presentation.home

sealed class HomeUiEvent {
    data class FilterMenu(val selectedFilters: List<String>): HomeUiEvent()

    data class SearchMenu(val searchQuery: String): HomeUiEvent()
}
