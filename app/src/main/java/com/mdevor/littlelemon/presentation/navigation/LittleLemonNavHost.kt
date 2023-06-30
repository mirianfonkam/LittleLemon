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
    navigationViewState: StartNavigationUiState,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = navigationViewState.startDestination.route,
    ) {
        composable(HOME.route) {
            HomeScreen(
                onProfileClick = {
                    navController.navigate(PROFILE.route)
                }
            )
        }
        composable(LOGIN.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(HOME.route) {
                        popUpTo(LOGIN.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(PROFILE.route) {
            ProfileScreen(
                onLogoutClick = {
                    navController.navigate(LOGIN.route) {
                        popUpTo(HOME.route) {
                            inclusive = true
                        }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}