package com.mdevor.littlelemon.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
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
                                    painter = painterResource(R.drawable.ic_little_lemon_logo),
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
                                        .clip(CircleShape),
                                    painter = painterResource(R.drawable.ic_little_lemon_profile),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Navigate to profile"
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.secondary)
                                .padding(all = 20.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = "Little Lemon",
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Text(
                                text = "Chicago",
                                color = MaterialTheme.colorScheme.surface,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Row() {
                                Text(
                                    text = "We are a family owned Mediterranean restaurant," +
                                            "focused on traditional recipes served with a modern twist.",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}