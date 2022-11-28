package com.mykhailo.vasylenko.feature.currency.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mykhailo.vasylenko.core.models.ExchangeItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyVM @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currencyType: ExchangeItemType =
        ExchangeItemType.valueOf(checkNotNull(savedStateHandle["type"]))
}