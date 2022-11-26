package com.mykhailo.vasylenko.app.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykhailo.vasylenko.app.data.ExchangeCurrenciesRepository
import com.mykhailo.vasylenko.app.ui.state.MainScreenState
import com.mykhailo.vasylenko.common.exeption.SnackbarMessage
import com.mykhailo.vasylenko.common.state.MessageState
import com.mykhailo.vasylenko.common.vm.executeAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
internal class MainActivityVM @Inject constructor(
    private val repository: ExchangeCurrenciesRepository
) : ViewModel() {

    private val messageState = MutableStateFlow(
        MessageState(
            message = null,
            onMessageShowed = { setMessage(null) }
        )
    )

    private val appStartupCompleted = MutableStateFlow(false)

    val screenState: StateFlow<MainScreenState> = combine(
        messageState,
        appStartupCompleted
    ) { message, isCompleted ->
        MainScreenState(
            messageState = message,
            appStartupCompleted = isCompleted
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = MainScreenState(
            messageState = messageState.value,
            appStartupCompleted = appStartupCompleted.value
        )
    )

    init {
        cacheExchangeCurrencies()
    }

    private fun cacheExchangeCurrencies() {
        executeAction(
            onError = { setMessage(it) },
            toDo = {
                repository.loadExchangeCurrencies()
                appStartupCompleted.emit(true)
            }
        )
    }

    private fun setMessage(message: SnackbarMessage?) {
        messageState.update {
            it.copy(message = message)
        }
    }
}