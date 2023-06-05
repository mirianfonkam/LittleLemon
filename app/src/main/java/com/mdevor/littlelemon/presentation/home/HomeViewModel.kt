package com.mdevor.littlelemon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.domain.usecase.GetCategoriesUseCase
import com.mdevor.littlelemon.domain.usecase.GetMenuUseCase
import com.mdevor.littlelemon.presentation.mapper.toPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMenuUseCase: GetMenuUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getMenuList()
    }

    fun dispatchViewEvent(viewAction: HomeUiEvent) {
        when (viewAction) {
            is HomeUiEvent.FilterMenu -> filterMenu(selectedFilters = viewAction.selectedFilters)
            is HomeUiEvent.SearchMenu -> updateSearchInput(input = viewAction.searchQuery)
        }
    }

    private fun getMenuList() {
        viewModelScope.launch {
            val menuList = getMenuUseCase()
            _uiState.update { it.copy(menuList = menuList.toPresentation()) }
            getCategoryList(menuList)
        }
    }

    private fun getCategoryList(menuList: List<MenuItem>) {
        val categoryList = getCategoriesUseCase(menuList)
        _uiState.update { it.copy(categoryList = categoryList) }
    }

    private fun updateSearchInput(input: String) {
        _uiState.update { it.copy(searchQuery = input) }
    }

    private fun filterMenu(selectedFilters: List<String>) {
        _uiState.update { it.copy(selectedCategoryList = selectedFilters) }
    }
}