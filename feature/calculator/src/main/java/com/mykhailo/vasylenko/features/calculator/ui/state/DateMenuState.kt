package com.mykhailo.vasylenko.features.calculator.ui.state

import java.time.LocalDateTime

data class DateMenuState(
    val displayDate: String,
    val onDateSelected: (LocalDateTime) -> Unit
)
