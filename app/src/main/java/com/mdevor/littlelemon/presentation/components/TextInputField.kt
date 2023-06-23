package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    textFieldState: String,
    onTextValueChange: (String) -> Unit,
    placeholderText: String? = null,
    labelText: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {
    Column(modifier = modifier) {
        labelText?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = 24.dp,
                    bottom = 4.dp
                )
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldState,
            onValueChange = { newText ->
                onTextValueChange(newText)
            },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
            ),
            shape = RectangleShape,
            textStyle = MaterialTheme.typography.bodySmall,
            placeholder = {
                Text(
                    text = placeholderText.orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        )
    }
}

@Composable
fun BasicTextInputField(
    textFieldState: String,
    onTextValueChange: (String) -> Unit,
    placeholderText: String? = null,
    labelText: String? = null,
) {
    TextInputField(
        textFieldState = textFieldState,
        onTextValueChange = onTextValueChange,
        placeholderText = placeholderText,
        labelText = labelText,
        backgroundColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth(),
    )
}