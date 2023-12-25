package com.mdevor.littlelemon

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.mdevor.littlelemon.presentation.navigation.LittleLemonDestination
import com.mdevor.littlelemon.presentation.navigation.LittleLemonNavHost
import com.mdevor.littlelemon.presentation.navigation.StartNavigationUiState
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class ScreenNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Test
    fun givenEmptyNavigationViewState_whenViewOpens_thenAssertRouteIsHomeScreen() {
        // WHEN
        setupLittleLemonNavHost()

        // THEN
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            LittleLemonDestination.HOME.route
        )
    }

    @Test
    fun givenLoginStartDestinationState_whenViewOpens_thenAssertRouteIsLoginScreen() {
        // WHEN
        setupLittleLemonNavHost(
            navigationViewState = StartNavigationUiState(
                startDestination = LittleLemonDestination.LOGIN
            )
        )

        // THEN
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            LittleLemonDestination.LOGIN.route
        )
    }

    @Test
    fun givenLoginStartDestinationState_whenRegisterSuccessfully_thenAssertRouteIsHomeScreen() {
        // GIVEN
        setupLittleLemonNavHost(
            navigationViewState = StartNavigationUiState(
                startDestination = LittleLemonDestination.LOGIN
            )
        )

        // WHEN
        enterUserLoginDetailsAndRegister()

        // THEN
        composeTestRule.waitUntilAtLeastOneExists(hasContentDescription("Little Lemon Logo"))
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            LittleLemonDestination.HOME.route
        )
    }

    @Test
    fun givenLoginStartDestinationState_whenRegisterSuccessfully_thenAssertCannotGoBackToLogin() {
        // GIVEN
        setupLittleLemonNavHost(
            navigationViewState = StartNavigationUiState(
                startDestination = LittleLemonDestination.LOGIN
            )
        )

        // WHEN
        enterUserLoginDetailsAndRegister()

        // THEN
        composeTestRule.waitUntilAtLeastOneExists(hasContentDescription("Little Lemon Logo"))
        assert(navController.backStack.none {
            it.destination.route == LittleLemonDestination.LOGIN.route
        })
    }

    @Test
    fun givenHomeStartDestinationState_whenClickOnProfile_thenAssertRouteIsProfileScreen() {
        // GIVEN
        setupLittleLemonNavHost(
            navigationViewState = StartNavigationUiState(
                startDestination = LittleLemonDestination.HOME
            )
        )

        // WHEN
        composeTestRule.onNodeWithContentDescription("Navigate to profile")
            .performClick()

        // THEN
        composeTestRule.waitUntilExactlyOneExists(
            hasContentDescription("Your profile picture")
        )
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            LittleLemonDestination.PROFILE.route
        )
    }

    @Test
    fun givenNavigateToProfile_whenClickOnLogOut_thenAssertRouteIsLoginScreen() {
        // GIVEN
        setupLittleLemonNavHost(
            navigationViewState = StartNavigationUiState(
                startDestination = LittleLemonDestination.HOME
            )
        )
        composeTestRule.onNodeWithContentDescription("Navigate to profile")
            .performClick()

        // WHEN
        composeTestRule.onNodeWithText("Log out").performClick()

        // THEN
        composeTestRule.waitUntilExactlyOneExists(hasText("Let's get to know you"))
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            LittleLemonDestination.LOGIN.route
        )
    }

    @Test
    fun givenNavigatedToProfile_whenClickOnLogOut_thenAssertCannotGoBackToProfileNorHomeScreens() {
        // GIVEN
        setupLittleLemonNavHost(
            navigationViewState = StartNavigationUiState(
                startDestination = LittleLemonDestination.HOME
            )
        )
        composeTestRule.onNodeWithContentDescription("Navigate to profile")
            .performClick()

        // WHEN
        composeTestRule.onNodeWithText("Log out").performClick()

        // THEN
        composeTestRule.waitUntilExactlyOneExists(hasText("Let's get to know you"))
        assert(navController.backStack.none {
            it.destination.route == LittleLemonDestination.PROFILE.route ||
                    it.destination.route == LittleLemonDestination.HOME.route
        })
    }

    @Test
    fun givenNavigatedToProfile_whenClickBackButton_thenAssertRouteIsHomeScreen() {
        // GIVEN
        setupLittleLemonNavHost(
            navigationViewState = StartNavigationUiState(
                startDestination = LittleLemonDestination.HOME
            )
        )
        composeTestRule.onNodeWithContentDescription("Navigate to profile")
            .performClick()

        // WHEN
        composeTestRule.onNodeWithContentDescription("Back button").performClick()

        // THEN
        composeTestRule.waitUntilExactlyOneExists(hasText("Little Lemon"))
        assertEquals(
            navController.currentBackStackEntry?.destination?.route,
            LittleLemonDestination.HOME.route
        )
    }


    private fun setupLittleLemonNavHost(
        navigationViewState: StartNavigationUiState = StartNavigationUiState()
    ) {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            LittleLemonTheme {
                LittleLemonNavHost(
                    navController = navController,
                    navigationViewState = navigationViewState
                )
            }
        }
    }

    private fun enterUserLoginDetailsAndRegister() {
        with(composeTestRule) {
            onNodeWithContentDescription("Enter Your First Name")
                .performTextInput("Mirian")
            onNodeWithContentDescription("Enter Your Last Name")
                .performTextInput("Fonkam")
            onNodeWithContentDescription("Enter Your Email Address")
                .performTextInput("mirianandkotlin@gmail.com")
            onNodeWithText("Register")
                .performClick()
        }
    }
}