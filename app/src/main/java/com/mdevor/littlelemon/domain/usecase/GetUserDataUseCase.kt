package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.entity.UserEntity
import com.mdevor.littlelemon.domain.repository.LittleLemonRepository

class GetUserDataUseCase(private val repository: LittleLemonRepository) {

    operator fun invoke(): UserEntity {
        return repository.getUserData()
    }
}