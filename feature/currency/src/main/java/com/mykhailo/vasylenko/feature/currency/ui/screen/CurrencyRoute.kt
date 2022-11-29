package com.mykhailo.vasylenko.feature.currency.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.common.event.DataEvent
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.core.models.ExchangeItemSelectionResult
import com.mykhailo.vasylenko.designsytem.components.Toolbar
import com.mykhailo.vasylenko.feature.currency.ui.screen.components.CurrencyItem
import com.mykhailo.vasylenko.feature.currency.ui.state.CurrencyScreenState

@Composable
fun CurrencyRoute(
    viewModel: CurrencyVM,
    navigateUp: () -> Unit,
    showMessage: (SnackbarMessage) -> Unit,
    onCurrencySelected: (DataEvent<ExchangeItemSelectionResult>) -> Unit,
) {
    val screenState by viewModel.screenState.collectAsState()

    CurrencyScreen(
        screenState = screenState,
        onCurrencySelected = onCurrencySelected,
        navigateUp = navigateUp,
        showMessage = showMessage
    )
}

@Composable
internal fun CurrencyScreen(
    screenState: CurrencyScreenState,
    navigateUp: () -> Unit,
    showMessage: (SnackbarMessage) -> Unit,
    onCurrencySelected: (DataEvent<ExchangeItemSelectionResult>) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar(
            title = "Select currency",
            navigateUp = navigateUp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = screenState.currencies
            ) { item ->
                CurrencyItem(
                    item = item,
                    onClicked = {
                        onCurrencySelected(
                            DataEvent(
                                ExchangeItemSelectionResult(
                                    code = it.code,
                                    name = it.name
                                )
                            )
                        )
                    }
                )
            }
        }
    }

    LaunchedEffect(screenState.messageState) {
        screenState.messageState.message?.let { message ->
            showMessage(message)
            screenState.messageState.onMessageShowed()
        }
    }
}