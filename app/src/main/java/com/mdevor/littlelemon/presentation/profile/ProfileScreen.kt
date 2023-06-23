package com.mdevor.littlelemon.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.components.LemonButton
import com.mdevor.littlelemon.presentation.components.LineDivider
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                IconButton(onClick = { /* TODO: Impl back click */ }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.surface,
                        contentDescription = "Back",
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(size = 200.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.ic_little_lemon_profile),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Your profile picture"
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "First Name")
            Text(text = "Tilly")
            LineDivider(
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        LemonButton(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .height(height = 48.dp)
                .fillMaxWidth(),
            text = "Logout",
            onClick = {
                // TODO: Impl register click
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    LittleLemonTheme {
        ProfileScreen()
    }
}