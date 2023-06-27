package com.mdevor.littlelemon.domain.usecase

import com.mdevor.littlelemon.domain.repository.LittleLemonRepository

class SetIsLoggedUseCase(private val repository: LittleLemonRepository) {

    operator fun invoke(isLogged: Boolean) {
        repository.setIsLogged(isLogged)
    }
}