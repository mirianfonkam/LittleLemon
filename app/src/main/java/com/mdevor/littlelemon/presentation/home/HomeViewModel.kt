package com.mdevor.littlelemon.presentation.home

import androidx.lifecycle.ViewModel
import com.mdevor.littlelemon.presentation.tempStub
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    /**
     * Warning: Avoid launching asynchronous operations in the init block or constructor of a ViewModel.
     * https://developer.android.com/topic/architecture/ui-layer/state-production
     */
    init {
        _uiState.update { it.copy(displayedMenuList = listOf(tempStub) + listOf(tempStub)) }
    }

    fun updateSearchInput(input: String) {
        _uiState.update { it.copy(searchTerm = input) }
    }
}