package com.mykhailo.vasylenko.feature.currency.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mykhailo.vasylenko.common.event.DataEvent
import com.mykhailo.vasylenko.core.models.ExchangeItemSelectionResult
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

@Composable
fun CurrencyRoute(
    viewModel: CurrencyVM,
    navigateUp: () -> Unit,
    onCurrencySelected: (DataEvent<ExchangeItemSelectionResult>) -> Unit,
) {
    CurrencyScreen()
}

@Composable
internal fun CurrencyScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Select currency",
            color = ApplicationTheme.colors.text,
            style = ApplicationTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            textAlign = TextAlign.Center
        )
    }
}