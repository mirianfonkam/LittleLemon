package com.mdevor.littlelemon.presentation.home

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val viewState: HomeUiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val viewEvent: (HomeUiEvent) -> Unit = { viewModel.dispatchViewEvent(it)}

    HomeScreenContent(viewState, viewEvent)
}

@Composable
fun HomeScreenContent(viewState: HomeUiState, viewEvent: (HomeUiEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar()
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
        ) {
            HeroBannerContent()
            val searchTextState = remember { mutableStateOf("") }
            TextInputField(
                textFieldState = searchTextState,
                onTextValueChange = { /* viewModel::updateSearchInput */ },
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                placeholderText = "Search",

                )
            Spacer(modifier = Modifier.height(36.dp))
        }
        var selectedCategories by remember { mutableStateOf(listOf<String>()) }
        FilterList(
            categories = viewState.categoryList,
            selectedCategories = selectedCategories,
            onFilterClick = { filter ->
                // Move callback to VM
                val oldCategoryList: MutableList<String> = selectedCategories.toMutableList()
                if (oldCategoryList.contains(filter)) {
                    oldCategoryList.remove(filter)
                } else {
                    oldCategoryList.add(filter)
                }

                selectedCategories = oldCategoryList
            }
        )
        LineDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        DishItemList(viewState.menuList)
    }
}

@Composable
private fun HomeTopBar() {
    Box() {
        LogoTopBar()
        ProfileTopBar(onProfileClick = {})
    }
}

@Composable
private fun DishItemList(dishList: List<MenuItemData>) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        itemsIndexed(items = dishList) { _, item ->
            DishItem(
                menuItem = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
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
        HomeScreen()
    }
}