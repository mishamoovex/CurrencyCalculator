package com.mykhailo.vasylenko.features.calculator.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.common.vm.executeAction
import com.mykhailo.vasylenko.core.models.ExchangeItemSelectionResult
import com.mykhailo.vasylenko.dispatchers.DispatcherDefault
import com.mykhailo.vasylenko.dispatchers.DispatcherIo
import com.mykhailo.vasylenko.features.calculator.data.ExchangeStatRepository
import com.mykhailo.vasylenko.features.calculator.domain.ConvertCurrencyUseCase
import com.mykhailo.vasylenko.features.calculator.ui.state.CalculatorScreenState
import com.mykhailo.vasylenko.features.calculator.ui.state.DateState
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeCardState
import com.mykhailo.vasylenko.features.calculator.ui.state.ExchangeItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalculatorVM @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    @DispatcherDefault private val defaultDispatcher: CoroutineDispatcher,
    private val statsRepository: ExchangeStatRepository,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    private val messageState = MutableStateFlow(
        MessageState(
            message = null,
            onMessageShowed = { setMessage(null) }
        )
    )

    private val dateState = MutableStateFlow(
        DateState(
            displayDate = "Click here to select date",
            selectedDate = null,
            onDateSelected = ::setDate
        )
    )

    private val originCurrencyState = MutableStateFlow(
        ExchangeItemState(
            value = "",
            onValueChanged = ::setOriginValue,
            currency = buildDisplayCurrencyText(
                code = "UAH",
                name = "Українська гривня"
            ),
            currencyCode = "UAH",
            isLoading = false,
            isFieldEnabled = false
        )
    )

    private val targetCurrencyState = MutableStateFlow(
        ExchangeItemState(
            value = "",
            onValueChanged = ::setTargetValue,
            currency = "Select target currency",
            currencyCode = null,
            isLoading = false,
            isFieldEnabled = false
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
                itemOriginal = origin.copy(
                    isFieldEnabled = target.currencyCode != null
                ),
                itemTarget = target.copy(
                    isFieldEnabled = target.currencyCode != null
                )
            ),
            showCurrencyCalculator = date.selectedDate != null,
            onCurrencySelected = ::setCurrency
        )
    }
        .flowOn(defaultDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CalculatorScreenState(
                messageState = messageState.value,
                dateState = dateState.value,
                cardState = ExchangeCardState(
                    itemOriginal = originCurrencyState.value,
                    itemTarget = targetCurrencyState.value
                ),
                showCurrencyCalculator = false,
                onCurrencySelected = ::setCurrency
            )
        )

    private fun setDate(date: LocalDate) {
        dateState.update {
            it.copy(
                selectedDate = date,
                displayDate = date.toDisplayDate()
            )
        }
        loadStats()
    }

    private fun loadStats() {
        executeAction(
            dispatcher = ioDispatcher,
            onError = { message ->
                updateFieldsLoadingState(
                    isLoading = false,
                    isEnabled = false
                )
                setMessage(message)
            },
            onLoading = {
                updateFieldsLoadingState(
                    isLoading = true,
                    isEnabled = false
                )
            },
            toDo = {
                statsRepository.loadStat(
                    date = dateState.value.selectedDate
                )

                updateFieldsLoadingState(
                    isLoading = false,
                    isEnabled = true
                )

                val value = originCurrencyState.value.value
                val currencyCode = targetCurrencyState.value.currencyCode

                if (value.isNotEmpty() && !currencyCode.isNullOrEmpty()) {
                    convertCurrency(
                        isOriginChanged = true,
                        value = value,
                        targetCurrencyCode = currencyCode
                    )
                }
            }
        )
    }

    private fun updateFieldsLoadingState(
        isLoading: Boolean,
        isEnabled: Boolean
    ) {
        originCurrencyState.update {
            it.copy(
                isFieldEnabled = isEnabled,
                isLoading = isLoading
            )
        }
        targetCurrencyState.update {
            it.copy(
                isFieldEnabled = isEnabled,
                isLoading = isLoading
            )
        }
    }

    private fun setCurrency(data: ExchangeItemSelectionResult) {
        executeAction(
            onError = { setMessage(it) },
            toDo = {
                val target = targetCurrencyState.updateAndGet {
                    it.copy(
                        currencyCode = data.code,
                        currency = buildDisplayCurrencyText(
                            code = data.code,
                            name = data.name
                        ),
                        isFieldEnabled = true
                    )
                }
                val origin = originCurrencyState.updateAndGet {
                    it.copy(
                        isFieldEnabled = true
                    )
                }
                convertCurrency(
                    isOriginChanged = true,
                    value = origin.value,
                    targetCurrencyCode = target.currencyCode
                        ?: throw IllegalStateException("Currency code not found")
                )
            }
        )
    }

    private fun setOriginValue(value: String) {
        executeAction(
            onError = { setMessage(it) },
            toDo = {
                originCurrencyState.update {
                    it.copy(value = value)
                }
                convertCurrency(
                    isOriginChanged = true,
                    value = value.replaceSpaces(),
                    targetCurrencyCode = targetCurrencyState.value.currencyCode
                        ?: throw IllegalStateException("Currency code not found")
                )
            }
        )
    }

    private fun setTargetValue(value: String) {
        executeAction(
            onError = { setMessage(it) },
            toDo = {
                targetCurrencyState.update {
                    it.copy(value = value)
                }
                convertCurrency(
                    isOriginChanged = false,
                    value = value.replaceSpaces(),
                    targetCurrencyCode = targetCurrencyState.value.currencyCode
                        ?: throw IllegalStateException("Currency code not found")
                )
            }
        )
    }

    private fun convertCurrency(
        isOriginChanged: Boolean,
        value: String,
        targetCurrencyCode: String
    ) {
        executeAction(
            dispatcher = ioDispatcher,
            onError = { setMessage(it) },
            toDo = {
                val convertedValue = if (value.isNotEmpty()) {
                    convertCurrencyUseCase(isOriginChanged, value, targetCurrencyCode)
                } else {
                    ""
                }

                if (isOriginChanged) {
                    targetCurrencyState.update {
                        it.copy(value = convertedValue)
                    }
                } else {
                    originCurrencyState.update {
                        it.copy(value = convertedValue)
                    }
                }
            }
        )
    }

    private fun setMessage(message: SnackbarMessage?) {
        messageState.update {
            it.copy(message = message)
        }
    }

    private fun buildDisplayCurrencyText(
        code: String,
        name: String
    ): String = "$name ($code)"

    private fun LocalDate.toDisplayDate() =
        "$year.$monthValue.$dayOfMonth"


    private fun String.replaceSpaces() = replace(" ", "")

}