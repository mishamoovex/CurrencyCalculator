package com.mykhailo.vasylenko.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mykhailo.vasylenko.app.ui.components.SnackbarManager
import com.mykhailo.vasylenko.app.ui.util.registerForResult
import com.mykhailo.vasylenko.app.ui.util.sendNavigationResult
import com.mykhailo.vasylenko.feature.currency.ui.screen.CurrencyRoute
import com.mykhailo.vasylenko.feature.history.ui.screen.ExchangeHistoryRoute
import com.mykhailo.vasylenko.features.calculator.ui.screen.CalculatorRoute

internal sealed class MainGraph(val route: String) {
    object Calculator : MainGraph(route = "calculator") {
        const val RESULT_KEY_CURRENCY = "target_currency"
    }

    object History : MainGraph(route = "history")

    object CurrencySelection : MainGraph(route = "currency")
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

        composable(MainGraph.Calculator.route) { backStack ->
            CalculatorRoute(
                viewModel = hiltViewModel(),
                selectCurrency = {
                    navHostController.navigate(MainGraph.CurrencySelection.route)
                },
                showMessage = snackbarManager::showMessage,
                selectedCurrency = backStack.registerForResult(
                    key = MainGraph.Calculator.RESULT_KEY_CURRENCY
                ),
                toTransactions = {
                    navHostController.navigate(MainGraph.History.route)
                }
            )
        }

        composable(MainGraph.History.route) {
            ExchangeHistoryRoute(
                viewModel = hiltViewModel(),
                navigateUp = {
                    navHostController.navigateUp()
                },
                showMessage = snackbarManager::showMessage
            )
        }

        composable(MainGraph.CurrencySelection.route) {
            CurrencyRoute(
                viewModel = hiltViewModel(),
                navigateUp = navHostController::navigateUp,
                onCurrencySelected = {
                    with(navHostController) {
                        sendNavigationResult(
                            key = MainGraph.Calculator.RESULT_KEY_CURRENCY,
                            data = it
                        )
                        navigateUp()
                    }
                },
                showMessage = snackbarManager::showMessage
            )
        }

    }
}