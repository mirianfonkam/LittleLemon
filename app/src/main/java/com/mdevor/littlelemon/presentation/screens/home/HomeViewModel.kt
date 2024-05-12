package com.mdevor.littlelemon.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdevor.littlelemon.domain.entity.MenuEntity
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

    fun handleViewAction(viewAction: HomeUiAction) {
        when (viewAction) {
            is HomeUiAction.FilterMenu -> handleFilteringUpdate(filter = viewAction.filter)
            is HomeUiAction.SearchMenu -> handleSearchUpdate(input = viewAction.searchQuery)
            is HomeUiAction.ClickOnProfile -> handleProfileClick()
            is HomeUiAction.ClearHomeEvent -> handleClearHomeEvent()
        }
    }

    private fun handleClearHomeEvent() {
        _uiState.update { it.copy(homeEvent = null) }
    }

    private fun handleProfileClick() {
        _uiState.update { it.copy(homeEvent = HomeVMEvent.NavigateToProfile) }
    }

    private fun getMenuList() {
        viewModelScope.launch {
            val menu = getMenuUseCase()
            val menuItemDataList = menu.toPresentation()
            _uiState.update {
                it.copy(
                    menuList = menuItemDataList,
                    displayedMenuList = menuItemDataList,
                )
            }
            getCategoryList(menu)
        }
    }

    private fun getCategoryList(menuList: List<MenuEntity>) {
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
            val query = searchQuery.filterNot { it.isWhitespace() }
            val filteredMenuList = menuList.filter { menuItem ->
                shouldSelectBySearchQuery(menuItem, query)
                        && shouldSelectByFilterList(selectedCategoryList, menuItem)
            }
            _uiState.update { it.copy(displayedMenuList = filteredMenuList) }
        }
    }

    private fun shouldSelectBySearchQuery(menuItem: MenuItemData, searchQuery: String): Boolean {
        return searchQuery.isBlank() || menuItem.title.contains(searchQuery, ignoreCase = true)
    }

    private fun shouldSelectByFilterList(
        selectedCategoryList: List<String>,
        menuItem: MenuItemData
    ): Boolean {
        return selectedCategoryList.isEmpty() || selectedCategoryList.contains(menuItem.category)
    }
}