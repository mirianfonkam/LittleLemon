package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.entity.MenuItem
import com.mdevor.littlelemon.domain.repository.LittleLemonRepository

class GetMenuUseCase(private val repository: LittleLemonRepository) {

    suspend operator fun invoke(): List<MenuItem> {
        return repository.getMenu()
    }
}