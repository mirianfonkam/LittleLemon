package com.mdevor.littlelemon.presentation.viewmodel

import com.mdevor.littlelemon.presentation.model.MenuItemData

data class MenuUiState(
    val searchTerm: String = String(),
    val selectedCategoryList: List<String> = emptyList(),
    val menuList: List<MenuItemData> = emptyList(),
    val displayedMenuList: List<MenuItemData> = menuList,
)