package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.entity.MenuEntity

class GetCategoriesUseCase {

    operator fun invoke(menuList: List<MenuEntity>): List<String> {
        return menuList.map { it.category }.distinct()
    }
}