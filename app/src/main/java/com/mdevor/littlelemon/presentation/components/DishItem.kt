package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.model.MenuItemData
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme

@Composable
fun DishItem(
    modifier: Modifier,
    menuItem: MenuItemData,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = menuItem.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.spacing_xx_small)
                )
            )
            Text(
                text = menuItem.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = menuItem.price,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacing_xx_small)
                )
            )
        }
        AsyncImage(
            modifier = Modifier
                .size( dimensionResource(id = R.dimen.menu_item_image_size))
                .align(CenterVertically),
            model = menuItem.image,
            contentDescription = stringResource(
                R.string.dish_item_image_content_desc,
                menuItem.title
            ),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DishItemPreview() {
    LittleLemonTheme {
        DishItem(
            modifier = Modifier.fillMaxWidth(),
            menuItem = MenuItemData(
                title = "Pasta",
                description = "Delicious pasta for your delight.",
                image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
                category = "mains",
                price = "$6.99",
            )
        )
    }
}