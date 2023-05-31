package com.mdevor.littlelemon.presentation.home

import com.mdevor.littlelemon.presentation.model.MenuItemData

data class HomeUiState(
    val searchTerm: String = "",
    val selectedCategoryList: List<String> = emptyList(),
    val menuList: List<MenuItemData> = emptyList(),
    val displayedMenuList: List<MenuItemData> = emptyList(),
)