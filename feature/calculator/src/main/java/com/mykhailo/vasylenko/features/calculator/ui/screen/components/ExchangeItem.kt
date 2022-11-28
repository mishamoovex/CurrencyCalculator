package com.mykhailo.vasylenko.features.calculator.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeItemState

@Composable
fun ExchangeItem(
    modifier: Modifier = Modifier,
    state: ExchangeItemState,
    onSelectCurrencyClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        ExchangeCurrency(
            currency = state.currency,
            buttonTitle = state.buttonTitle,
            isLoading = state.isLoading,
            onSelectCurrencyClicked = onSelectCurrencyClicked
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        CustomTextField(
            value = state.value,
            onValueChange = state.onValueChanged,
            singleLine = true,
            maxLines = 1,
            textStyle = ApplicationTheme.typography.h2,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
            placeholder = {
                Text(
                    text = "0.0",
                    style = ApplicationTheme.typography.h2
                )
            },
            enabled = state.isFieldEnabled
        )
    }
}

@Composable
private fun ExchangeCurrency(
    modifier: Modifier = Modifier,
    currency: String?,
    buttonTitle: String,
    isLoading: Boolean,
    onSelectCurrencyClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (currency == null) {
            ActionButton(
                onClick = onSelectCurrencyClicked,
                modifier = Modifier
                    .width(210.dp)
                    .height(50.dp),
                isLoading = isLoading,
            ) {
                Text(text = buttonTitle)
            }
        } else {
            Text(
                text = currency,
                color = ApplicationTheme.colors.text,
                style = ApplicationTheme.typography.subtitle1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExchangeItemPreview_HasCurrency() {
    val state = ExchangeItemState(
        value = "200.5",
        onValueChanged = {},
        currency = "USD - United States Dollar",
        buttonTitle = "Select original currency",
        isLoading = false,
        currencyCode = null,
        isFieldEnabled = true
    )
    ApplicationTheme {
        ExchangeItem(
            state = state,
            onSelectCurrencyClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExchangeItemPreview_NoCurrency() {
    val state = ExchangeItemState(
        value = "",
        onValueChanged = {},
        currency = null,
        buttonTitle = "Select original currency",
        isLoading = false,
        currencyCode = null,
        isFieldEnabled = true
    )
    ApplicationTheme {
        ExchangeItem(
            state = state,
            onSelectCurrencyClicked = {}
        )
    }
}