package com.mdevor.littlelemon.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.mdevor.littlelemon.domain.usecase.GetUserDataUseCase
import com.mdevor.littlelemon.domain.usecase.SetIsLoggedUseCase
import com.mdevor.littlelemon.presentation.mapper.toPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val setIsLoggedUseCase: SetIsLoggedUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                userInfoList = getUserDataUseCase().toPresentation()
            )
        }
    }

    fun handleViewAction(viewAction: ProfileUiAction) {
        when(viewAction) {
            is ProfileUiAction.ClickBackButton -> handleBackButtonClick()
            is ProfileUiAction.ClickLogoutButton -> handleLogoutButtonClick()
        }
    }

    private fun handleLogoutButtonClick() {
        setIsLoggedUseCase(isLogged = false)
        _uiState.update {
            it.copy(
                profileEvent = ProfileVMEvent.NavigateToLogin
            )
        }
    }

    private fun handleBackButtonClick() {
        _uiState.update {
            it.copy(
                profileEvent = ProfileVMEvent.NavigateBack
            )
        }
    }
}

