package com.mdevor.littlelemon.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mdevor.littlelemon.presentation.screens.home.HomeScreen
import com.mdevor.littlelemon.presentation.screens.login.LoginScreen
import com.mdevor.littlelemon.presentation.screens.profile.ProfileScreen
import com.mdevor.littlelemon.presentation.navigation.LittleLemonDestination.HOME
import com.mdevor.littlelemon.presentation.navigation.LittleLemonDestination.LOGIN
import com.mdevor.littlelemon.presentation.navigation.LittleLemonDestination.PROFILE

@Composable
fun LittleLemonNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LOGIN.route,
    ) {
        composable(HOME.route) {
            HomeScreen()
        }
        composable(LOGIN.route) {
            LoginScreen()
        }
        composable(PROFILE.route) {
            ProfileScreen()
        }
    }
}