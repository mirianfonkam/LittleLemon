package com.mdevor.littlelemon.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.components.LineDivider
import com.mdevor.littlelemon.presentation.components.TextInputField
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import com.mdevor.littlelemon.presentation.viewmodel.MenuViewModel

@Composable
fun HomeScreen(viewModel: MenuViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Image(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(height = 40.dp, width = 148.dp),
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "Little Lemon Logo"
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Image(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(size = 40.dp)
                            .clip(CircleShape)
                            .clickable { /* TODO nav to profile */ },
                    painter = painterResource(R.drawable.ic_little_lemon_profile),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Navigate to profile"
                    )
                }
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
                Row(modifier = Modifier
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
                val searchTextState = remember { mutableStateOf("") }
                TextInputField(
                    textFieldState = searchTextState,
                    backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                    placeholderText = "Search"
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
                items(5) { index ->
                    Spacer(modifier = Modifier.height(16.dp))
                    DishItem(menuItem = tempStub)
                    Spacer(modifier = Modifier.height(16.dp))
                    LineDivider(
                        color = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LittleLemonTheme {
        HomeScreen(MenuViewModel())
    }
}