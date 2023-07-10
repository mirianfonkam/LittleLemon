package com.mdevor.littlelemon.presentation.viewmodel

import com.mdevor.littlelemon.domain.usecase.GetCategoriesUseCase
import com.mdevor.littlelemon.domain.usecase.GetMenuUseCase
import com.mdevor.littlelemon.presentation.screens.home.HomeUiAction
import com.mdevor.littlelemon.presentation.screens.home.HomeUiState
import com.mdevor.littlelemon.presentation.screens.home.HomeVMEvent
import com.mdevor.littlelemon.presentation.screens.home.HomeViewModel
import com.mdevor.littlelemon.testhelpers.stubs.getCategoryList
import com.mdevor.littlelemon.testhelpers.stubs.getDomainMenuList
import com.mdevor.littlelemon.testhelpers.stubs.getPresentationMenuList
import com.mdevor.littlelemon.testhelpers.stubs.greekSaladItem
import com.mdevor.littlelemon.testhelpers.testrule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel

    private val getMenuUseCase = mockk<GetMenuUseCase>()
    private val getCategoriesUseCase = mockk<GetCategoriesUseCase>()

    @Before
    fun setupViewModel() {
        coEvery { getMenuUseCase() } returns getDomainMenuList()
        coEvery { getCategoriesUseCase(any()) } returns getCategoryList()

        viewModel = HomeViewModel(
            getMenuUseCase = getMenuUseCase,
            getCategoriesUseCase = getCategoriesUseCase,
        )
    }

    @Test
    fun `WHEN init THEN getMenuUseCase and getCategoriesUseCase are called in order`() = runTest {
        // GIVEN
        val expectedMenuList = getDomainMenuList()

        // THEN
        coVerifyOrder {
            getMenuUseCase.invoke()
            getCategoriesUseCase.invoke(expectedMenuList)
        }
    }

    @Test
    fun `WHEN init THEN assert uiState updates accordingly with menuList and categoryList states`() = runTest {
        // GIVEN
        val expectedState = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = getPresentationMenuList(),
            categoryList = getCategoryList(),
        )

        // THEN
        assertEquals(expectedState, viewModel.uiState.value)
    }


    @Test
    fun `WHEN handle SearchMenu uiAction with query THEN assert uiState updates according to found search result`() {
        // GIVEN
        val foundSearchQuery = "pasta"
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = listOf(getPresentationMenuList().first { it.title == "Pasta" }),
            categoryList = getCategoryList(),
            searchQuery = foundSearchQuery,
        )

        // WHEN
        viewModel.handleViewAction(HomeUiAction.SearchMenu(foundSearchQuery))

        // THEN
       assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `WHEN handle SearchMenu uiAction with blank query THEN assert uiState updates accordingly`() {
        // GIVEN
        val blankSearchQuery =  " "
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = getPresentationMenuList(),
            categoryList = getCategoryList(),
            searchQuery = blankSearchQuery,
        )

        // WHEN
        viewModel.handleViewAction(HomeUiAction.SearchMenu(blankSearchQuery))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `WHEN handle SearchMenu uiAction with not found query THEN assert uiState updates accordingly`() {
        // GIVEN
        val blankSearchQuery = "testing not found search query"
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = emptyList(),
            categoryList = getCategoryList(),
            searchQuery = blankSearchQuery,
        )

        // WHEN
        viewModel.handleViewAction(HomeUiAction.SearchMenu(blankSearchQuery))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `WHEN handle FilterMenu uiAction with selectedFilter THEN assert uiState updates according to found menuItem`() {
        // GIVEN
        val selectedFilter = getCategoryList().first()
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = listOf(getPresentationMenuList().first { it.category == selectedFilter }),
            categoryList = getCategoryList(),
            selectedCategoryList = listOf(selectedFilter),
        )

        // WHEN
        viewModel.handleViewAction(HomeUiAction.FilterMenu(selectedFilter))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `WHEN handle FilterMenu uiAction twice THEN assert uiState updates according to filter toggle`() {
        // GIVEN
        val selectedFilter = getCategoryList().first()
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = getPresentationMenuList(),
            categoryList = getCategoryList(),
            selectedCategoryList = emptyList(),
        )

        // WHEN
        viewModel.handleViewAction(HomeUiAction.FilterMenu(selectedFilter))
        viewModel.handleViewAction(HomeUiAction.FilterMenu(selectedFilter))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `WHEN handle FilterMenu uiAction and Search uiAction THEN assert uiState updates accordingly`() {
        // GIVEN
        val selectedFilter = getCategoryList().first()
        val searchQuery = "Salad"
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = listOf(greekSaladItem()),
            categoryList = getCategoryList(),
            selectedCategoryList = listOf(selectedFilter),
            searchQuery = searchQuery,
        )

        // WHEN
        viewModel.handleViewAction(HomeUiAction.FilterMenu(selectedFilter))
        viewModel.handleViewAction(HomeUiAction.SearchMenu(searchQuery))

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `WHEN handle ClickOnProfile uiAction THEN assert uiState updates with NavigateToProfile event`() {
        // GIVEN
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = getPresentationMenuList(),
            categoryList = getCategoryList(),
            homeEvent = HomeVMEvent.NavigateToProfile,
        )
        // WHEN
        viewModel.handleViewAction(HomeUiAction.ClickOnProfile)

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN ClickOnProfile WHEN handle ClearHomeEvent uiAction THEN assert uiState updates with null event`() {
        // GIVEN
        val expected = HomeUiState(
            menuList = getPresentationMenuList(),
            displayedMenuList = getPresentationMenuList(),
            categoryList = getCategoryList(),
            homeEvent = null,
        )
        viewModel.handleViewAction(HomeUiAction.ClickOnProfile)

        // WHEN
        viewModel.handleViewAction(HomeUiAction.ClearHomeEvent)

        // THEN
        assertEquals(expected, viewModel.uiState.value)
    }
}