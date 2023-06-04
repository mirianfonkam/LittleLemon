package com.mdevor.littlelemon.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.DishItem
import com.mdevor.littlelemon.presentation.FilterList
import com.mdevor.littlelemon.presentation.components.LineDivider
import com.mdevor.littlelemon.presentation.components.LogoTopBar
import com.mdevor.littlelemon.presentation.components.ProfileTopBar
import com.mdevor.littlelemon.presentation.components.TextInputField
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    // uiState
    // uiAction

    // home content (uiState, uiAction)
    Column(modifier = Modifier.fillMaxSize()) {
        Box() {
            LogoTopBar()
            ProfileTopBar(onProfileClick = {})
        }
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Little Lemon",
                style = MaterialTheme.typography.displayLarge,
            )
            Text(
                text = "Chicago",
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.displayMedium
            )
            HeroContentRow()
            val searchTextState = remember { mutableStateOf("") }
            TextInputField(
                textFieldState = searchTextState,
                onTextValueChange = viewModel::updateSearchInput,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                placeholderText = "Search",

            )
            Spacer(modifier = Modifier.height(36.dp))
        }
        var selectedCategories by remember { mutableStateOf(listOf<String>()) }
        FilterList(
            categories = listOf("Chip 1", "Chip 2", "Chip 3","Chip 4", "Chip 5", "Chip 6", "Chip 7","Chip 8"  ),
            selectedCategories = selectedCategories
        ) { filter ->
            // Move callback to VM
            val oldCategoryList: MutableList<String> = selectedCategories.toMutableList()
            if (oldCategoryList.contains(filter)) {
                oldCategoryList.remove(filter)
            } else {
                oldCategoryList.add(filter)
            }

            selectedCategories = oldCategoryList
        }
        LineDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            itemsIndexed(items = viewModel.uiState.value.displayedMenuList) { _, item ->
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
}

@Composable
private fun HeroContentRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 48.dp)
    ) {
        Text(
            text = "We are a family owned Mediterranean restaurant, " +
                    "focused on traditional recipes served with a modern twist.",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .weight(weight = 0.5f)
                .padding(end = 8.dp),
        )
        Image(
            painter = painterResource(R.drawable.hero_image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LittleLemonTheme {
        HomeScreen()
    }
}