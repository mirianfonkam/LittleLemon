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
            is HomeUiEvent.FilterMenu -> filterMenu(filter = viewAction.filter)
            is HomeUiEvent.SearchMenu -> updateSearchInput(input = viewAction.searchQuery)
        }
    }

    private fun getMenuList() {
        viewModelScope.launch {
            val menuList = getMenuUseCase()
            val menuItemDataList = menuList.toPresentation()
            _uiState.update {
                it.copy(
                    menuList = menuItemDataList,
                    displayedMenuList = menuItemDataList,
                )
            }
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

    private fun filterMenu(filter: String) {
        val selectedFilters: MutableList<String> = _uiState.value.selectedCategoryList.toMutableList()
        if (selectedFilters.contains(filter)) {
            selectedFilters.remove(filter)
        } else {
            selectedFilters.add(filter)
        }
        val newSelectedFilterList = selectedFilters.toList()
        val newDisplayedMenuList = if (newSelectedFilterList.isEmpty()) {
            _uiState.value.menuList
        } else {
            _uiState.value.menuList.filter { menuItem ->
                newSelectedFilterList.contains(menuItem.category)
            }
        }
        _uiState.update {
            it.copy(
                selectedCategoryList = newSelectedFilterList,
                displayedMenuList = newDisplayedMenuList,
            )
        }
    }
}