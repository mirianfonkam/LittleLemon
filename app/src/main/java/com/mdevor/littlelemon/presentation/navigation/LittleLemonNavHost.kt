package com.mdevor.littlelemon.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mdevor.littlelemon.presentation.screens.home.HomeScreen
import com.mdevor.littlelemon.presentation.screens.login.LoginScreen
import com.mdevor.littlelemon.presentation.screens.profile.ProfileScreen
import com.mdevor.littlelemon.presentation.navigation.LittleLemonDestinations.HOME
import com.mdevor.littlelemon.presentation.navigation.LittleLemonDestinations.LOGIN
import com.mdevor.littlelemon.presentation.navigation.LittleLemonDestinations.PROFILE

@Composable
fun LittleLemonNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = LOGIN.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(HOME.route) {
            HomeScreen()
        }
        composable(LOGIN.route) {
            LoginScreen(
                onLoginSuccessCallback = {
                    navController.navigate(HOME.route) {
                        popUpTo(LOGIN.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(PROFILE.route) {
            ProfileScreen()
        }
    }
}