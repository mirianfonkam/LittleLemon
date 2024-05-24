package com.mdevor.littlelemon.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.components.DishItem
import com.mdevor.littlelemon.presentation.components.FilterList
import com.mdevor.littlelemon.presentation.components.HeroBannerContent
import com.mdevor.littlelemon.presentation.components.LineDivider
import com.mdevor.littlelemon.presentation.components.LogoTopBar
import com.mdevor.littlelemon.presentation.components.ProfileTopBar
import com.mdevor.littlelemon.presentation.components.TextInputField
import com.mdevor.littlelemon.presentation.model.MenuItemData
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onProfileClick: () -> Unit = {},
) {
    val viewState: HomeUiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val viewAction: (HomeUiAction) -> Unit = { viewModel.handleViewAction(it)}
    HomeScreenContent(viewState, viewAction)
    HomeScreenEffect(viewState, viewAction, onProfileClick)
}

@Composable
fun HomeScreenEffect(viewState: HomeUiState, viewAction: (HomeUiAction) -> Unit, onProfileClick: () -> Unit) {
    viewState.homeEvent?.let { event ->
        LaunchedEffect(event) {
            when (event) {
                is HomeVMEvent.NavigateToProfile -> {
                    onProfileClick()
                }
            }
        }
        viewAction(HomeUiAction.ClearHomeEvent)
    }
}

@Composable
fun HomeScreenContent(viewState: HomeUiState, viewAction: (HomeUiAction) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar { viewAction(HomeUiAction.ClickOnProfile) }
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_small))
                .fillMaxWidth(),
        ) {
            HeroBannerContent()
            TextInputField(
                textFieldState = viewState.searchQuery,
                onTextValueChange = { searchQuery ->
                    viewAction(HomeUiAction.SearchMenu(searchQuery))
                },
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                placeholderText = stringResource(R.string.search),
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_x_large)))
        }
        FilterList(
            categories = viewState.categoryList,
            selectedCategories = viewState.selectedCategoryList,
            onFilterClick = { filter -> viewAction(HomeUiAction.FilterMenu(filter)) }
        )
        LineDivider(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        DishItemList(viewState.displayedMenuList)
    }
}

@Composable
private fun HomeTopBar(onProfileClick: () -> Unit) {
    Box() {
        LogoTopBar()
        ProfileTopBar(onProfileClick = onProfileClick)
    }
}

@Composable
private fun DishItemList(dishList: List<MenuItemData>) {
    val dishItemListContentDescription = stringResource(R.string.menu)
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium))
            .semantics {
                contentDescription = dishItemListContentDescription
            },
    ) {
        itemsIndexed(items = dishList) { _, item ->
            DishItem(
                menuItem = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.spacing_x_large))
            )
            LineDivider(
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LittleLemonTheme {
        HomeScreenContent(
            viewState = HomeUiState(),
            viewAction = {}
        )
    }
}