package com.mykhailo.vasylenko.features.calculator.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.components.elevationShadow
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeCardState
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeItemState

@Composable
fun ExchangeCard(
    modifier: Modifier = Modifier,
    state: ExchangeCardState,
    selectCurrency: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .elevationShadow(),
        color = ApplicationTheme.colors.cardBackground,
        shape = ApplicationTheme.shapes.large
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
        ) {
            ExchangeItem(
                state = state.itemOriginal
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = ApplicationTheme.colors.textHint,
                thickness = 1.dp
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            ExchangeItem(
                state = state.itemTarget,
                onSelectCurrencyClicked = {
                    selectCurrency()
                }
            )
        }
    }
}

@Preview
@Composable
private fun ExchangeCardPreview() {
    val stateOrigin = ExchangeItemState(
        value = "200.67",
        onValueChanged = {},
        currency = "USD - United States Dollar",
        isLoading = false,
        currencyCode = null,
        isFieldEnabled = true
    )
    val stateTarget = ExchangeItemState(
        value = "",
        onValueChanged = {},
        currency = "Select currency",
        isLoading = false,
        currencyCode = null,
        isFieldEnabled = true
    )
    val state = ExchangeCardState(
        stateOrigin,
        stateTarget
    )
    ApplicationTheme {
        ExchangeCard(
            state = state,
            selectCurrency = {}
        )
    }
}