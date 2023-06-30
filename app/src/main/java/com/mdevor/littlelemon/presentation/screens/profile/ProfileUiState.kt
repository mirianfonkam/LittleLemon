package com.mdevor.littlelemon.presentation.screens.profile

import com.mdevor.littlelemon.presentation.model.InfoData

data class ProfileUiState(
    val userInfoList: List<InfoData> = emptyList(),
    val profileEvent: ProfileVMEvent? = null,
)