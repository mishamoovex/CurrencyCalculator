package com.mykhailo.vasylenko.app.ui.calculator.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mykhailo.vasylenko.app.ui.calculator.screen.components.ApplicationSnackbar
import com.mykhailo.vasylenko.app.ui.calculator.state.CalculatorScreenState
import com.mykhailo.vasylenko.app.ui.calculator.state.rememberBaseScreenState
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

@Composable
fun CalculatorRoute(
    viewModel: CalculatorVM
) {

    val screenState by viewModel.screenState.collectAsState()

    CalculatorScreen(
        screenState = screenState
    )
}

@Composable
fun CalculatorScreen(
    screenState: CalculatorScreenState
) {
    val appState = rememberBaseScreenState()
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = false,
    )

    Scaffold(
        scaffoldState = appState.scaffoldState,
        snackbarHost = { ApplicationSnackbar(it) },
        backgroundColor = ApplicationTheme.colors.surface
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
        ) {

        }
    }
}