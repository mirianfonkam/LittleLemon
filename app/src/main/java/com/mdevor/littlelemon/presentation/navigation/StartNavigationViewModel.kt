package com.mdevor.littlelemon.presentation.navigation

import androidx.lifecycle.ViewModel
import com.mdevor.littlelemon.domain.usecase.GetIsLoggedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StartNavigationViewModel(
    getIsLoggedUseCase: GetIsLoggedUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(StartNavigationUiState())
    val uiState: StateFlow<StartNavigationUiState> = _uiState.asStateFlow()

    init {
        updateStartDestinationByLoginStatus(getIsLoggedUseCase)
    }

    private fun updateStartDestinationByLoginStatus(getIsLoggedUseCase: GetIsLoggedUseCase) {
        val isUserLogged = getIsLoggedUseCase()
        val startDestination = if (isUserLogged) {
            LittleLemonDestination.HOME
        } else {
            LittleLemonDestination.LOGIN
        }
        _uiState.update {
            it.copy(
                startDestination = startDestination
            )
        }
    }
}