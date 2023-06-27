package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.repository.LittleLemonRepository

class GetIsLoggedUseCase(private val repository: LittleLemonRepository) {

    operator fun invoke(): Boolean {
        return repository.getIsLogged()
    }
}