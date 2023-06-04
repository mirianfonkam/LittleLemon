package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.entity.MenuItem

class GetCategoriesUseCase {

    operator fun invoke(menuList: List<MenuItem>): List<String> {
        return menuList.map { it.category }.distinct()
    }
}