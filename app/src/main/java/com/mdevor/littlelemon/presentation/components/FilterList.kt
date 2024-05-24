package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.mdevor.littlelemon.R

@Composable
fun FilterList(
    categories: List<String>,
    selectedCategories: List<String>,
    onFilterClick: (String) -> Unit,
) {
    LazyRow(
        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.spacing_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_medium)),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.spacing_small)
        ),
    ) {
        items(items = categories) { filter ->
            val isSelected = filter in selectedCategories
            FilterChip(
                selected = isSelected,
                onClick = { onFilterClick(filter) },
                label = { Text(text = filter) },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.spacing_medium)),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    labelColor = MaterialTheme.colorScheme.secondary,
                    selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    selectedLabelColor = MaterialTheme.colorScheme.surface,
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = Color.Transparent,
                    selectedBorderColor = MaterialTheme.colorScheme.onSurface,
                    enabled = true,
                    selected = isSelected,
                )
            )
        }
    }
}