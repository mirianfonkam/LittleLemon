package com.mdevor.littlelemon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.domain.usecase.GetCategoriesUseCase
import com.mdevor.littlelemon.domain.usecase.GetMenuUseCase
import com.mdevor.littlelemon.presentation.mapper.toPresentation
import com.mdevor.littlelemon.presentation.model.MenuItemData
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
            is HomeUiEvent.FilterMenu -> handleFilteringUpdate(filter = viewAction.filter)
            is HomeUiEvent.SearchMenu -> handleSearchUpdate(input = viewAction.searchQuery)
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

    private fun handleSearchUpdate(input: String) {
        _uiState.update { it.copy(searchQuery = input) }
        updateDisplayedMenuList()
    }

    private fun handleFilteringUpdate(filter: String) {
        val selectedFilters = _uiState.value.selectedCategoryList.toMutableList()
        toggleFilter(selectedFilters, filter)
        val newSelectedFilterList = selectedFilters.toList()
        _uiState.update { it.copy(selectedCategoryList = newSelectedFilterList) }
        updateDisplayedMenuList()
    }

    private fun toggleFilter(
        selectedFilters: MutableList<String>,
        filter: String,
    ) {
        val wasPreviouslySelected = selectedFilters.contains(filter)
        if (wasPreviouslySelected) {
            selectedFilters.remove(filter)
        } else {
            selectedFilters.add(filter)
        }
    }

    private fun updateDisplayedMenuList() {
        with(_uiState.value) {
            val filteredMenuList = menuList.filter { menuItem ->
                shouldSelectBySearchQuery(menuItem, searchQuery)
                        && shouldSelectByFilterList(selectedCategoryList, menuItem)
            }
            _uiState.update { it.copy(displayedMenuList = filteredMenuList) }
        }
    }

    private fun shouldSelectBySearchQuery(menuItem: MenuItemData, searchQuery: String): Boolean {
        return menuItem.title.contains(searchQuery, ignoreCase = true) || searchQuery.isBlank()
    }

    private fun shouldSelectByFilterList(
        selectedCategoryList: List<String>,
        menuItem: MenuItemData
    ): Boolean {
        return selectedCategoryList.contains(menuItem.category) || selectedCategoryList.isEmpty()
    }
}