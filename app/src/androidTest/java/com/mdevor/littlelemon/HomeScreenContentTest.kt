package com.mdevor.littlelemon

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import com.mdevor.littlelemon.presentation.model.MenuItemData
import com.mdevor.littlelemon.presentation.screens.home.HomeScreenContent
import com.mdevor.littlelemon.presentation.screens.home.HomeUiAction
import com.mdevor.littlelemon.presentation.screens.home.HomeUiState
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class HomeScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewActionMock: (HomeUiAction) -> Unit = mockk(relaxed = true)

    @Test
    fun whenNavigateToProfile_thenAssertClickOnProfileAction() {
        // GIVEN
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(),
                    viewAction = mockViewActionMock
                )
            }
        }

        // WHEN
        composeTestRule.onNodeWithContentDescription("Navigate to profile")
            .performClick()

        // THEN
        verify(exactly = 1) { mockViewActionMock(HomeUiAction.ClickOnProfile) }
    }

    @Test
    fun whenSearchQueryChanged_thenAssertSearchMenuAction() {
        // GIVEN
        val textInput = "Burger"
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(),
                    viewAction = mockViewActionMock
                )
            }
        }

        // WHEN
        composeTestRule.onNodeWithText("Search").performTextInput(textInput)

        // THEN
        verify(exactly = 1) { mockViewActionMock(HomeUiAction.SearchMenu(textInput)) }
    }

    @Test
    fun whenSearchQueryIsEmpty_thenAssertSearchPlaceholderIsVisible() {
        // GIVEN
        val emptySearchQuery = ""

        // WHEN
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(
                        searchQuery = emptySearchQuery
                    ),
                    viewAction = mockViewActionMock
                )
            }
        }

        // THEN
        composeTestRule.onNodeWithText("Search").assertIsDisplayed()
    }

    @Test
    fun whenSearchQueryIsPizza_thenAssertPizzaIsVisible() {
        // GIVEN
        val pizzaSearchQuery = "Pizza"

        // WHEN
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(
                        searchQuery = pizzaSearchQuery
                    ),
                    viewAction = mockViewActionMock
                )
            }
        }

        // THEN
        composeTestRule.onNodeWithText(pizzaSearchQuery).assertIsDisplayed()
    }

    @Test
    fun whenScreenOpens_thenAssertStaticTextIsVisible() {
        // WHEN
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(),
                    viewAction = mockViewActionMock
                )
            }
        }

        // THEN
        with(composeTestRule) {
            onNodeWithText("Little Lemon").assertIsDisplayed()
            onNodeWithText("Chicago").assertIsDisplayed()
            onNodeWithText("We are a family owned Mediterranean restaurant, " +
                    "focused on traditional recipes served with a modern twist."
            ).assertIsDisplayed()
        }
    }

    @Test
    fun givenFilterList_whenScreenOpens_thenAssertFiltersAreVisible() {
        // GIVEN
        val firstFilter = "starters"
        val secondFilter = "mains"

        // WHEN
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(
                        categoryList = listOf(firstFilter, secondFilter)
                    ),
                    viewAction = mockViewActionMock
                )
            }
        }

        // THEN
        with(composeTestRule) {
            onNodeWithText(firstFilter).assertIsDisplayed()
            onNodeWithText(secondFilter).assertIsDisplayed()
        }
    }

    @Test
    fun givenFilterList_whenClickOnMainsFilter_thenAssertFilterMenuAction() {
        // GIVEN
        val firstFilter = "starters"
        val secondFilter = "mains"
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(
                        categoryList = listOf(firstFilter, secondFilter)
                    ),
                    viewAction = mockViewActionMock
                )
            }
        }

        // WHEN
        composeTestRule.onNodeWithText(secondFilter).performClick()


        // THEN
        verify(exactly = 1) { mockViewActionMock(HomeUiAction.FilterMenu(secondFilter)) }
    }

    @Test
    fun givenMenuList_whenScreenOpens_thenAssertMenuItemsAreVisible() {
        // GIVEN
        val menuList = listOf(
            MenuItemData(
                title = "Pasta",
                description = "Delicious pasta for your delight.",
                image = "imageURL",
                category = "mains",
                price = "$6.99",
            ),
            MenuItemData(
                title = "Greek Salad",
                description = "Our delicious salad is served with Feta cheese and peeled cucumber. Includes tomatoes, onions, olives, salt and oregano in the ingredients.",
                image = "imageURL",
                category = "starters",
                price = "$12.99",
            )
        )


        // WHEN
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(displayedMenuList = menuList),
                    viewAction = mockViewActionMock
                )
            }
        }

        // THEN
        with(composeTestRule) {
            assertMenuItemIsDisplayed(menuList.component1())
            assertMenuItemIsDisplayed(menuList.component2())
        }
    }

    @Test
    fun givenSelectedCategory_whenScreenOpens_thenAssertFilterAreSelectedAccordingly() {
        // GIVEN
        val categories = listOf("Fast Food", "Italian", "Asian")
        val selectedCategory = "Italian"
        val selectedCategories = listOf(selectedCategory)

        // WHEN
        composeTestRule.setContent {
            LittleLemonTheme {
                HomeScreenContent(
                    viewState = HomeUiState(
                        categoryList = categories,
                        selectedCategoryList = selectedCategories
                    ),
                    viewAction = mockViewActionMock
                )
            }
        }

        // THEN
        with(composeTestRule) {
            categories.filterNot { it == selectedCategory }.forEach { category ->
                onNodeWithText(category)
                    .assertIsDisplayed()
                    .assertIsNotSelected()
            }
            onNodeWithText(selectedCategory)
                .assertIsDisplayed()
                .assertIsSelected()
        }
    }

    private fun ComposeContentTestRule.assertMenuItemIsDisplayed(
        menuItem: MenuItemData,
    ) {
        with(onNodeWithContentDescription("Menu")) {
            performScrollToNode(hasText(menuItem.title))
            onNodeWithText(menuItem.title).assertIsDisplayed()

            performScrollToNode(hasText(menuItem.description))
            onNodeWithText(menuItem.description).assertIsDisplayed()

            performScrollToNode(hasText(menuItem.price))
            onNodeWithText(menuItem.price).assertIsDisplayed()
        }
    }
}