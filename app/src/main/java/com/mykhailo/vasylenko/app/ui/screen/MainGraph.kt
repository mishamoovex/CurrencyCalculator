package com.mykhailo.vasylenko.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mykhailo.vasylenko.app.ui.components.SnackbarManager
import com.mykhailo.vasylenko.features.calculator.ui.screen.CalculatorRoute

internal sealed class MainGraph(val route: String) {
    object Calculator : MainGraph(route = "calculator")

    object History : MainGraph(route = "history")
}

@Composable
internal fun MainNavHost(
    snackbarManager: SnackbarManager,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = MainGraph.Calculator.route,
        modifier = modifier
    ) {

        composable(MainGraph.Calculator.route) {
            CalculatorRoute(
                viewModel = hiltViewModel()
            )
        }

        composable(MainGraph.History.route) {
            CalculatorRoute(
                viewModel = hiltViewModel()
            )
        }

    }
}