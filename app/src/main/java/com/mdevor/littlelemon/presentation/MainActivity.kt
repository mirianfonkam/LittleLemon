package com.mdevor.littlelemon.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.mdevor.littlelemon.presentation.navigation.LittleLemonNavHost
import com.mdevor.littlelemon.presentation.navigation.StartNavigationViewModel
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<StartNavigationViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                val viewState = viewModel.uiState.collectAsStateWithLifecycle().value

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    LittleLemonNavHost(
                        navController = navController,
                        navigationViewState = viewState,
                    )
                }
            }
        }
    }
}