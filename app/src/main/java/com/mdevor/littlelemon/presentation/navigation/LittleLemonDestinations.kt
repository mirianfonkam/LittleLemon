package com.mdevor.littlelemon.presentation.navigation

enum class LittleLemonDestinations {
    HOME,
    PROFILE,
    LOGIN;

    val route: String
        get() = name.lowercase()
}