package com.mykhailo.vasylenko.feature.history.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.designsytem.components.Toolbar
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.feature.history.ui.screen.components.ExchangeTransactionItem
import com.mykhailo.vasylenko.feature.history.ui.state.ExchangeHistoryScreenState

@Composable
fun ExchangeHistoryRoute(
    viewModel: ExchangeHistoryVM,
    navigateUp: () -> Unit,
    showMessage: (SnackbarMessage) -> Unit
    ) {
    val screenState by viewModel.screenState.collectAsState()

    ExchangeHistoryScreen(
        screenState = screenState,
        navigateUp = navigateUp,
        showMessage = showMessage
    )
}

@Composable
internal fun ExchangeHistoryScreen(
    screenState: ExchangeHistoryScreenState,
    navigateUp: () -> Unit,
    showMessage: (SnackbarMessage) -> Unit
    ) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar(
            title = "Exchange transactions history",
            navigateUp = navigateUp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (screenState.currencies != null) {
            if (screenState.currencies.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        items = screenState.currencies,
                        key = { it.hashCode() }
                    ) { item ->
                        ExchangeTransactionItem(item = item)
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No exchange transaction found",
                        style = ApplicationTheme.typography.h6,
                        color = ApplicationTheme.colors.text
                    )
                }
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