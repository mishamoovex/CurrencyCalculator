package com.mykhailo.vasylenko.features.calculator.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.designsytem.components.elevationShadow
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import com.mykhailo.vasylenko.features.calculator.ui.screen.components.CurrentDate
import com.mykhailo.vasylenko.features.calculator.ui.screen.components.ExchangeCard
import com.mykhailo.vasylenko.features.calculator.ui.screen.components.rememberDatePickerDialog
import com.mykhailo.vasylenko.features.calculator.ui.state.CalculatorScreenState
import com.mykhailo.vasylenko.core.models.ExchangeItemType

@Composable
fun CalculatorRoute(
    viewModel: CalculatorVM,
    selectCurrency: (ExchangeItemType) -> Unit,
    showMessage: (SnackbarMessage) -> Unit
) {

    val screenState by viewModel.screenState.collectAsState()

    CalculatorScreen(
        screenState = screenState,
        selectCurrency = selectCurrency,
        showMessage = showMessage
    )
}

@Composable
internal fun CalculatorScreen(
    screenState: CalculatorScreenState,
    selectCurrency: (ExchangeItemType) -> Unit,
    showMessage: (SnackbarMessage) -> Unit
) {
    val datePickerDialog = rememberDatePickerDialog(
        onDateSelected = screenState.dateState.onDateSelected
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Currency calculator",
            color = ApplicationTheme.colors.text,
            style = ApplicationTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(26.dp)
        )
        CurrentDate(
            displayDate = screenState.dateState.displayDate,
            onOpenCalendarClicked = {
                datePickerDialog.show()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .elevationShadow()
        )
        if (screenState.showCurrencyCalculator){
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            ExchangeCard(
                state = screenState.cardState,
                selectCurrency = selectCurrency,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

    LaunchedEffect(screenState.messageState) {
        screenState.messageState.message?.let { message ->
            showMessage(message)
            screenState.messageState.onMessageShowed()
        }
    }
}
