package com.mdevor.littlelemon.presentation.screens.login

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mdevor.littlelemon.R
import com.mdevor.littlelemon.presentation.components.BasicTextInputField
import com.mdevor.littlelemon.presentation.components.LemonButton
import com.mdevor.littlelemon.presentation.components.LogoTopBar
import com.mdevor.littlelemon.presentation.theme.LittleLemonTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit = {},
) {
    val viewState: LoginUiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val viewAction: (LoginUiAction) -> Unit = { viewModel.handleViewAction(it) }

    LoginScreenContent(viewState, viewAction)
    LoginScreenEffect(viewState, onLoginSuccess)
}

@Composable
private fun LoginScreenEffect(
    viewState: LoginUiState,
    onLoginSuccess: () -> Unit,
) {
    viewState.loginEvent?.let { event ->
        LaunchedEffect(event) {
            when (event) {
                is LoginVMEvent.NavigateToHome -> {
                    onLoginSuccess()
                }
            }
        }
    }
}

@Composable
fun LoginScreenContent(
    viewState: LoginUiState,
    viewAction: (LoginUiAction) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LogoTopBar()
        OnboardingBanner()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_small)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))
                BasicTextInputField(
                    textFieldState = viewState.firstName,
                    onTextValueChange = { firstName ->
                        viewAction(LoginUiAction.UpdateFirstName(firstName))
                    },
                    labelText = "First Name",
                    contentDescription = "Enter Your First Name",
                )
                BasicTextInputField(
                    textFieldState = viewState.lastName,
                    onTextValueChange = { lastName ->
                        viewAction(LoginUiAction.UpdateLastName(lastName))
                    },
                    labelText = "Last Name",
                    contentDescription = "Enter Your Last Name",
                )
                BasicTextInputField(
                    textFieldState = viewState.email,
                    onTextValueChange = { email ->
                        viewAction(LoginUiAction.UpdateEmail(email))
                    },
                    labelText = "Email",
                    contentDescription = "Enter Your Email Address",
                    keyboardType = KeyboardType.Email,
                )
            }
            LemonButton(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.spacing_large))
                    .height(height = dimensionResource(id = R.dimen.spacing_xx_large))
                    .fillMaxWidth(),
                text = "Register",
                onClick = {
                    viewAction(LoginUiAction.ClickRegisterButton)
                }
            )
        }
    }
    if (viewState.loginStatusMessage.isNotEmpty()) {
        Toast.makeText(
            LocalContext.current,
            viewState.loginStatusMessage,
            Toast.LENGTH_SHORT
        ).show()
        viewAction(LoginUiAction.HideLoginStatusMessage)
    }
}

@Composable
private fun OnboardingBanner() {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(vertical = dimensionResource(id = R.dimen.spacing_x_large))
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
        LoginScreenContent(
            viewState = LoginUiState(),
            viewAction = {}
        )
    }
}