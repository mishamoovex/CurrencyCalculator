package com.mykhailo.vasylenko.app.ui.calculator.screen.components

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

@Composable
fun ApplicationSnackbar(hostState: SnackbarHostState) {
    SnackbarHost(hostState) { snackbarData ->
        Snackbar(
            snackbarData = snackbarData,
            modifier = Modifier.systemBarsPadding(),
            backgroundColor = ApplicationTheme.colors.backgroundSnackbar,
            contentColor = ApplicationTheme.colors.textOnSnackbar
        )
    }
}