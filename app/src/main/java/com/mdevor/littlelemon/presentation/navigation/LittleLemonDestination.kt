package com.mdevor.littlelemon.presentation.navigation

enum class LittleLemonDestination {
    HOME,
    PROFILE,
    LOGIN;

    val route: String
        get() = name.lowercase()
}