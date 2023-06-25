package com.mdevor.littlelemon.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mdevor.littlelemon.presentation.components.BasicTextInputField
import com.mdevor.littlelemon.presentation.components.LemonButton
import com.mdevor.littlelemon.presentation.components.LogoTopBar
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val viewState: LoginUiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val viewEvent: (LoginUiEvent) -> Unit = { viewModel.dispatchViewEvent(it) }
    LoginScreenContent(viewState, viewEvent)
}

@Composable
fun LoginScreenContent(viewState: LoginUiState, viewEvent: (LoginUiEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LogoTopBar()
        OnBoardingBanner()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                BasicTextInputField(
                    textFieldState = viewState.firstName,
                    onTextValueChange = { firstName ->
                        viewEvent(LoginUiEvent.UpdateFirstName(firstName))
                    },
                    labelText = "First Name",
                )
                BasicTextInputField(
                    textFieldState = viewState.lastName,
                    onTextValueChange = { lastName ->
                        viewEvent(LoginUiEvent.UpdateLastName(lastName))
                    },
                    labelText = "Last Name",
                )
                BasicTextInputField(
                    textFieldState = viewState.email,
                    onTextValueChange = { email ->
                        viewEvent(LoginUiEvent.UpdateEmail(email))
                    },
                    labelText = "Email",
                )

            }
            LemonButton(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .height(height = 48.dp)
                    .fillMaxWidth(),
                text = "Register",
                onClick = {
                    viewEvent(LoginUiEvent.OnRegisterButtonClicked)
                }
            )
        }
    }
}

@Composable
private fun OnBoardingBanner() {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(vertical = 36.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Let's get to know you",
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.displayMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LittleLemonTheme {
        LoginScreen()
    }
}