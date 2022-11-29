package com.mykhailo.vasylenko.feature.history.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.common.vm.executeAction
import com.mykhailo.vasylenko.dispatchers.DispatcherIo
import com.mykhailo.vasylenko.feature.history.data.ExchangeHistoryRepository
import com.mykhailo.vasylenko.feature.history.domain.ExchangeTransaction
import com.mykhailo.vasylenko.feature.history.ui.state.ExchangeHistoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ExchangeHistoryVM @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val repository: ExchangeHistoryRepository
) : ViewModel() {

    private val messageState = MutableStateFlow(
        MessageState(
            message = null,
            onMessageShowed = { setMessage(null) }
        )
    )

    private val currencies = MutableStateFlow<List<ExchangeTransaction>?>(null)

    val screenState: StateFlow<ExchangeHistoryScreenState> = combine(
        messageState,
        currencies
    ) { m, c ->
        ExchangeHistoryScreenState(
            messageState = m,
            currencies = c
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ExchangeHistoryScreenState(
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
                repository.loadHistory()
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