package com.mdevor.littlelemon.presentation.model

import androidx.annotation.StringRes

data class InfoData(
    @StringRes val label: Int,
    val value: String,
)