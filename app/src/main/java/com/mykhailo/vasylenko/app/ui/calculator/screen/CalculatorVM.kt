package com.mykhailo.vasylenko.app.ui.calculator.screen

import androidx.lifecycle.ViewModel
import com.mykhailo.vasylenko.app.ui.calculator.state.CalculatorScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorVM @Inject constructor() : ViewModel() {

    private val _screenState = MutableStateFlow(
        CalculatorScreenState()
    )
    val screenState = _screenState.asStateFlow()
}