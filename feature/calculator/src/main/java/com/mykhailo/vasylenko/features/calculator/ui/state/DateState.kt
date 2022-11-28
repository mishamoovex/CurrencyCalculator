package com.mykhailo.vasylenko.features.calculator.ui.state

import java.time.LocalDate

data class DateState(
    val selectedDate: LocalDate?,
    val displayDate: String,
    val onDateSelected: (LocalDate) -> Unit
)
