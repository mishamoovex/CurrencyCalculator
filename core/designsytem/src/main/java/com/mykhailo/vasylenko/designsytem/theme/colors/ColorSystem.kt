package com.mykhailo.vasylenko.designsytem.theme.colors

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
class ApplicationColors(

)

internal val LocalApplicationColors = staticCompositionLocalOf<ApplicationColors> {
    error("No MamaTalkColors provided")
}

@Composable
internal fun ProvideApplicationColors(
    colors: ApplicationColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalApplicationColors provides colors, content = content)
}

@Composable
internal fun ProvideRippleTheme(
    theme: RippleTheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides theme, content = content)
}

@Composable
internal fun ProvideTextSelectionColors(
    selectionColor: TextSelectionColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalTextSelectionColors provides selectionColor, content = content)
}