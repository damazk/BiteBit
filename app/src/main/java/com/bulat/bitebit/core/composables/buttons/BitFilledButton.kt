package com.bulat.bitebit.core.composables.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BitFilledButton(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    containerColor: Color = Color(0xFFE3851B),
    contentColor: Color = Color.White,
    onClick: () -> Unit,
    trailingIcon: ImageVector? = null
) = Button(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor
    ),
    content = {
        Text(text)
        if (trailingIcon != null) {
            Spacer(Modifier.width(10.dp))
            Icon(
                imageVector = trailingIcon,
                contentDescription = trailingIcon.name
            )
        }
    }
)