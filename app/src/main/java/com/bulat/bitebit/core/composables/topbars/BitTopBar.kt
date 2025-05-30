package com.bulat.bitebit.core.composables.topbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BitTopBar(
    title: String,
    titleColor: Color = Color.White,
    containerColor: Color = Color(0xFFE3851B),
    modifier: Modifier = Modifier.fillMaxWidth(),
    onNavigationIconClick: (() -> Unit)? = null
) {

    val title = @Composable {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold
        )
    }

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            if (onNavigationIconClick != null) {

                IconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = Icons.AutoMirrored.Rounded.ArrowBack.name,
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = titleColor,
            containerColor = containerColor
        )
    )
}