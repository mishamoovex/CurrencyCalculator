package com.mykhailo.vasylenko.features.calculator.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeItemState

@Composable
fun ExchangeItem(
    modifier: Modifier = Modifier,
    state: ExchangeItemState,
    onSelectCurrencyClicked: (() -> Unit)? = null   
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        if (onSelectCurrencyClicked != null){
            ActionButton(
                onClick = onSelectCurrencyClicked,
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
                    .clickable {
                        onSelectCurrencyClicked()
                    },
                isLoading = state.isLoading,
            ) {
                Text(
                    text = state.currency,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }else {
           Text(
               text = state.currency,
               color = ApplicationTheme.colors.text,
               style = ApplicationTheme.typography.subtitle1
           )
        }
        
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

@Preview(showBackground = true)
@Composable
private fun ExchangeItemPreview_HasCurrency() {
    val state = ExchangeItemState(
        value = "200.5",
        onValueChanged = {},
        currency = "USD - United States Dollar",
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
        currency = "Select currency",
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