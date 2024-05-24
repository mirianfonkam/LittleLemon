package com.mdevor.littlelemon.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import com.mdevor.littlelemon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    textFieldState: String,
    onTextValueChange: (String) -> Unit,
    contentDescription: String? = null,
    placeholderText: String? = null,
    labelText: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    Column(modifier = modifier) {
        labelText?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacing_medium),
                    bottom = dimensionResource(id = R.dimen.spacing_xx_small)
                )
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().semantics {
                contentDescription?.let { this.contentDescription = it }
            },
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
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
        )
    }
}

@Composable
fun BasicTextInputField(
    textFieldState: String,
    onTextValueChange: (String) -> Unit,
    contentDescription: String? = null,
    placeholderText: String? = null,
    labelText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    TextInputField(
        textFieldState = textFieldState,
        onTextValueChange = onTextValueChange,
        contentDescription = contentDescription,
        placeholderText = placeholderText,
        labelText = labelText,
        backgroundColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth(),
        keyboardType = keyboardType,
    )
}