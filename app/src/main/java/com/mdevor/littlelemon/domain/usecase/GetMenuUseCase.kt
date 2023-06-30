package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.entity.MenuEntity
import com.mdevor.littlelemon.domain.repository.LittleLemonRepository

class GetMenuUseCase(private val repository: LittleLemonRepository) {

    suspend operator fun invoke(): List<MenuEntity> {
        return repository.getMenu()
    }
}