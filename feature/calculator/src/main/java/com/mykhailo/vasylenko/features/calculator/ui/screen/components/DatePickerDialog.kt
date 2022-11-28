package com.mykhailo.vasylenko.features.calculator.ui.screen.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate

@Composable
fun rememberDatePickerDialog(
    context: Context = LocalContext.current,
    currentDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit
): DatePickerDialog = remember {
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            onDateSelected(LocalDate.of(year, month +1, dayOfMonth))
        },
        currentDate.year,
        currentDate.monthValue -1,
        currentDate.dayOfMonth
    )
}