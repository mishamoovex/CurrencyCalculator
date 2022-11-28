package com.mykhailo.vasylenko.features.calculator.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.dispatchers.DispatcherIo
import com.mykhailo.vasylenko.features.calculator.data.ExchangeStatRepository
import com.mykhailo.vasylenko.features.calculator.domain.model.ExchangeStat
import com.mykhailo.vasylenko.features.calculator.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalculatorVM @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val statsRepository: ExchangeStatRepository
) : ViewModel() {

    private val originalStats = MutableStateFlow<List<ExchangeStat>>(
        listOf()
    )

    private val dateState = MutableStateFlow(
        DateState(
            displayDate = "Click here to select date",
            selectedDate = null,
            onDateSelected = ::setDate
        )
    )

    private val messageState = MutableStateFlow(
        MessageState(
            message = null,
            onMessageShowed = { setMessage(null) }
        )
    )

    private val originCurrencyState = MutableStateFlow(
        ExchangeItemState(
            value = "",
            onValueChanged = {},
            currency = null,
            currencyCode = null,
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
            currencyCode = null,
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

    private fun setDate(date: LocalDate) {
        dateState.update {
            it.copy(
                selectedDate = date,
                displayDate = date.toDisplayDate()
            )
        }
    }

    fun setCurrency(
        type: ExchangeItemType,
        code: String,
        name: String
    ) {
        when (type) {
            ExchangeItemType.ORIGINAL -> {
                updateOriginalCurrencyField(code, name)
            }
            ExchangeItemType.TARGET -> {
                updateTargetCurrencyField(code, name)
            }
        }
    }

    private fun updateOriginalCurrencyField(
        code: String,
        name: String
    ) {

    }

    private fun updateTargetCurrencyField(
        code: String,
        name: String
    ) {
        targetCurrencyState.update {
            it.copy(
                currencyCode = code,
                currency = buildDisplayCurrencyText(code, name)
            )
        }
    }

    private fun buildDisplayCurrencyText(
        code: String,
        name: String
    ): String = "$code - $name"

    private fun LocalDate.toDisplayDate() =
        "$year.$monthValue.$dayOfMonth"
}