package com.mykhailo.vasylenko.feature.currency.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.common.vm.executeAction
import com.mykhailo.vasylenko.dispatchers.DispatcherIo
import com.mykhailo.vasylenko.feature.currency.data.CurrencyRepository
import com.mykhailo.vasylenko.feature.currency.domain.ExchangeCurrency
import com.mykhailo.vasylenko.feature.currency.ui.state.CurrencyScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CurrencyVM @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val repository: CurrencyRepository
) : ViewModel() {

    private val messageState = MutableStateFlow(
        MessageState(
            message = null,
            onMessageShowed = { setMessage(null) }
        )
    )

    private val currencies = MutableStateFlow<List<ExchangeCurrency>>(listOf())

    val screenState: StateFlow<CurrencyScreenState> = combine(
        messageState,
        currencies
    ) { m, c ->
        CurrencyScreenState(
            messageState = m,
            currencies = c
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CurrencyScreenState(
            messageState = messageState.value,
            currencies = currencies.value
        )
    )


    init {
        getCurrencies()
    }

    private fun getCurrencies() {
        executeAction(
            dispatcher = ioDispatcher,
            onError = { setMessage(it) },
            toDo = {
                repository.getCurrencies()
                    .also {
                        currencies.emit(it)
                    }
            }
        )
    }

    private fun setMessage(message: SnackbarMessage?) {
        messageState.update {
            it.copy(message = message)
        }
    }
}