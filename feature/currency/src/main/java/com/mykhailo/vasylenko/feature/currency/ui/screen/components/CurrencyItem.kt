package com.mykhailo.vasylenko.feature.currency.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.components.elevationShadow
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.feature.currency.domain.ExchangeCurrency

@Composable
internal fun CurrencyItem(
    modifier: Modifier = Modifier,
    item: ExchangeCurrency,
    onClicked: (ExchangeCurrency) -> Unit
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
                .clickable {
                    onClicked(item)
                }
                .padding(16.dp)
        ) {
            Text(
                text = item.name,
                style = ApplicationTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                text = item.code,
                style = ApplicationTheme.typography.body2,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun CurrencyItemPreview() {
    val item = ExchangeCurrency(
        name = "Українська гривня",
        code = "UAN"
    )
    ApplicationTheme {
        CurrencyItem(
            item = item,
            onClicked = {}
        )
    }
}