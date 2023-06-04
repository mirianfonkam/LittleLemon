package com.mdevor.littlelemon.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mdevor.littlelemon.presentation.home.HomeScreen
import com.mdevor.littlelemon.presentation.home.HomeViewModel
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
//                val viewModel: HomeViewModel = viewModel { HomeViewModel() }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
//                    HomeScreen(viewModel)
                }
            }
        }
    }
}