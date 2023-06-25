package com.mdevor.littlelemon.presentation.navigation

interface LittleLemonDestinations {
    val route: String
}

object HomeDestination : LittleLemonDestinations {
    override val route = "home"
}

object LoginDestination : LittleLemonDestinations {
    override val route = "login"
}

object ProfileDestination : LittleLemonDestinations {
    override val route = "profile"
}