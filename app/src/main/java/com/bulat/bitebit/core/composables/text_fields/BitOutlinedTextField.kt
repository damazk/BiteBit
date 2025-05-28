package com.bulat.bitebit.core.composables.text_fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun BitOutlinedTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    isError: Boolean = false,
    supportingText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    color: Color = Color(0xFFE3851B)
) = OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    label = {
        Text(labelText)
    },
    isError = isError,
    supportingText = {
        Text(supportingText)
    },
    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
    colors = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = color,
        focusedBorderColor = color
    )
)