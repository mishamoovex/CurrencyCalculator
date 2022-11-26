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
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeItemType

@Composable
fun ExchangeCard(
    modifier: Modifier = Modifier,
    state: ExchangeCardState,
    selectCurrency: (ExchangeItemType) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .elevationShadow(),
        color = ApplicationTheme.colors.surface,
        shape = ApplicationTheme.shapes.large
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            ExchangeItem(
                state = state.itemOriginal,
                onSelectCurrencyClicked = {
                    selectCurrency(ExchangeItemType.ORIGINAL)
                }
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
                    selectCurrency(ExchangeItemType.TARGET)
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
        buttonTitle = "Select original currency",
        isLoading = false,
        isFieldEnabled = true
    )
    val stateTarget = ExchangeItemState(
        value = "",
        onValueChanged = {},
        currency = null,
        buttonTitle = "Select target currency",
        isLoading = false,
        isFieldEnabled = false
    )
    val state = ExchangeCardState(
        stateOrigin, stateTarget
    )
    ApplicationTheme {
        ExchangeCard(
            state = state,
            selectCurrency = {}
        )
    }
}