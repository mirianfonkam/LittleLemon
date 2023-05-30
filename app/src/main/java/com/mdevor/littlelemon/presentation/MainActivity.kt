package com.mdevor.littlelemon.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import com.mdevor.littlelemon.presentation.viewmodel.MenuViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                HomeScreen(MenuViewModel())
            }
        }
    }
}