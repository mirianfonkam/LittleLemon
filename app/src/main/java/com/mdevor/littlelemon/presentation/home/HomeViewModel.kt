package com.mdevor.littlelemon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdevor.littlelemon.domain.usecase.GetMenuUseCase
import com.mdevor.littlelemon.presentation.mapper.toPresentation
import com.mdevor.littlelemon.presentation.tempStub
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMenuUseCase: GetMenuUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    /**
     * Warning: Avoid launching asynchronous operations in the init block or constructor of a ViewModel.
     * https://developer.android.com/topic/architecture/ui-layer/state-production
     */
    init {
        _uiState.update { it.copy(displayedMenuList = listOf(tempStub) + listOf(tempStub)) }
    }

    fun getMenu() {
        viewModelScope.launch {
            val menuList = getMenuUseCase().toPresentation()
            _uiState.update { it.copy(menuList = menuList) }
        }
    }

    fun updateSearchInput(input: String) {
        _uiState.update { it.copy(searchQuery = input) }
    }
}