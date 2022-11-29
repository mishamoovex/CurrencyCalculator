package com.mykhailo.vasylenko.feature.history.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.components.elevationShadow
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.feature.history.domain.ExchangeTransaction

@Composable
internal fun ExchangeTransactionItem(
    modifier: Modifier = Modifier,
    item: ExchangeTransaction
) {
    Surface(
        modifier = modifier.elevationShadow(
            borderRadius = 16.dp,
            shadowRadius = 16.dp
        ),
        shape = ApplicationTheme.shapes.medium,
        color = ApplicationTheme.colors.cardBackground,
        contentColor = ApplicationTheme.colors.text
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = item.transaction,
                style = ApplicationTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                text = item.timestamp,
                style = ApplicationTheme.typography.body2,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun CurrencyItemPreview() {
    val item = ExchangeTransaction(
        timestamp = "3 Jun 2008 11:05:30",
        transaction = "UAH 100 -> AUD 260.75"
    )
    ApplicationTheme {
        ExchangeTransactionItem(
            item = item
        )
    }
}