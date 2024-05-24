package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.model.InfoData

@Composable
fun InfoItem(info: InfoData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_small))
    ) {
        Text(
            text = stringResource(id = info.label),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = info.value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.spacing_medium),
            ),
        )
        LineDivider(
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.spacing_small))
        )
    }
}