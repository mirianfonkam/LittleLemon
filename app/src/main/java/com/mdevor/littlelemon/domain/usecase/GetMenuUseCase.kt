package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.domain.repository.MenuRepository

class GetMenuUseCase(private val menuRepository: MenuRepository) {

    suspend operator fun invoke(): List<MenuItem> {
        return menuRepository.getMenu()
    }
}