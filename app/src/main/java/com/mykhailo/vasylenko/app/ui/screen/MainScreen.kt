package com.mykhailo.vasylenko.app.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mykhailo.vasylenko.app.ui.components.ApplicationSnackbar
import com.mykhailo.vasylenko.app.ui.components.rememberBaseScreenState
import com.mykhailo.vasylenko.app.ui.state.MainScreenState
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import kotlinx.coroutines.delay

@Composable
internal fun MainScreenEntry(
    viewModel: MainActivityVM,
    hideSplashScreen: () -> Unit,
) {
    val uiState by viewModel.screenState.collectAsState()

    MainScreen(
        state = uiState,
        hideSplashScreen = hideSplashScreen
    )
}

@Composable
internal fun MainScreen(
    state: MainScreenState,
    hideSplashScreen: () -> Unit
) {
    ApplicationTheme {
        val appState = rememberBaseScreenState()
        val systemUiController = rememberSystemUiController()

        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true,
        )

        Scaffold(
            scaffoldState = appState.scaffoldState,
            snackbarHost = { ApplicationSnackbar(it) },
            backgroundColor = ApplicationTheme.colors.surface
        ) { paddings ->

            if (state.appStartupCompleted) {
                MainNavHost(
                    modifier = Modifier.padding(paddings),
                    navHostController = appState.navHostController,
                    snackbarManager = appState.snackbarManager
                )

                LaunchedEffect(Unit) {
                    delay(200)
                    hideSplashScreen()
                }
            }
        }

        LaunchedEffect(state.messageState) {
            state.messageState.message?.let { message ->
                appState.snackbarManager.showMessage(message)
                state.messageState.onMessageShowed()
            }
        }
    }
}
