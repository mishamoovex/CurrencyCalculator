package com.mykhailo.vasylenko.features.calculator.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.feature.calculator.R

@Composable
fun CurrentDate(
    modifier: Modifier = Modifier,
    displayDate: String,
    onOpenCalendarClicked: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(16.dp),
        color = ApplicationTheme.colors.backgroundButton,
        shape = ApplicationTheme.shapes.medium,
        contentColor = ApplicationTheme.colors.textOnButton
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CalendarButton(
                modifier = Modifier.clickable {
                    onOpenCalendarClicked()
                }
            )
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            Text(
                text = displayDate,
                style = ApplicationTheme.typography.subtitle2,
                color = ApplicationTheme.colors.textOnButton
            )
            Spacer(
                modifier = Modifier.width(16.dp)
            )
        }
    }
}

@Composable
private fun CalendarButton(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.size(48.dp),
        color = ApplicationTheme.colors.actionColor,
        contentColor = ApplicationTheme.colors.text,
        shape = ApplicationTheme.shapes.medium
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CalendarButtonPreview() {
    ApplicationTheme {
        CalendarButton()
    }
}

@Preview
@Composable
private fun CurrentDatePreview() {
    ApplicationTheme {
        CurrentDate(
            displayDate = "20.11.2022",
            onOpenCalendarClicked = {}
        )
    }
}