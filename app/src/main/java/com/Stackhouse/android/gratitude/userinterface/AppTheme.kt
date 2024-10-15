package com.Stackhouse.android.gratitude.userinterface

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun AppTheme(
    content: @Composable () -> Unit
) = MaterialTheme(
    colorScheme = lightColorScheme(
        primary = Color(0xFF0C4475),
        primaryContainer = Color(0xFFB85810),
        secondary = Color(0xFF8B1379),
        secondaryContainer = Color(0xFFD3B910),
        background = Color(0xFFE09C58)
    )
) {
    content()
}