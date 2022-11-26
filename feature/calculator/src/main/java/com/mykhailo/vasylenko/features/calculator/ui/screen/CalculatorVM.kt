package com.mykhailo.vasylenko.features.calculator.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.features.calculator.ui.state.CalculatorScreenState
import com.mykhailo.vasylenko.features.calculator.ui.state.DateState
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeCardState
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CalculatorVM @Inject constructor() : ViewModel() {

    private val messageState = MutableStateFlow(
        MessageState(
            message = null,
            onMessageShowed = { setMessage(null) }
        )
    )

    private val dateState = MutableStateFlow(
        DateState(
            displayDate = "26.11.2022",
            onDateSelected = {}
        )
    )

    private val originCurrencyState = MutableStateFlow(
        ExchangeItemState(
            value = "",
            onValueChanged = {},
            currency = null,
            isLoading = false,
            isFieldEnabled = false,
            buttonTitle = "Select origin currency"
        )
    )

    private val targetCurrencyState = MutableStateFlow(
        ExchangeItemState(
            value = "",
            onValueChanged = {},
            currency = null,
            isLoading = false,
            isFieldEnabled = false,
            buttonTitle = "Select trarget currency"
        )
    )

    val screenState: StateFlow<CalculatorScreenState> = combine(
        messageState,
        dateState,
        originCurrencyState,
        targetCurrencyState
    ) { message, date, origin, target ->
        CalculatorScreenState(
            messageState = message,
            dateState = date,
            cardState = ExchangeCardState(
                itemOriginal = origin,
                itemTarget = target
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CalculatorScreenState(
            messageState = messageState.value,
            dateState = dateState.value,
            cardState = ExchangeCardState(
                itemOriginal = originCurrencyState.value,
                itemTarget = targetCurrencyState.value
            )
        )
    )

    private fun setMessage(message: SnackbarMessage?) {
        messageState.update {
            it.copy(message = message)
        }
    }
}