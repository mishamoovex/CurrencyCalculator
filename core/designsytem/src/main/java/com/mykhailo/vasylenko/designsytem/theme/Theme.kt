@file:OptIn(ExperimentalFoundationApi::class)
@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.mykhailo.vasylenko.designsytem.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.mykhailo.vasylenko.designsytem.theme.colors.*

@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit
) {
    ProvideApplicationColors(colors = DarkColorPalette) {
        DisableOverscroll {
            MaterialTheme(
                colors = debugColors(),
                shapes = Shapes
            ) {
                ProvideTextSelectionColors(selectionColor = DarkTextSelectionColor) {
                    ProvideRippleTheme(theme = MamaTalkRippleTheme) {
                        content()
                    }
                }
            }
        }
    }
}

object ApplicationTheme {

    val colors: ApplicationColors
        @Composable
        get() = LocalApplicationColors.current

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography
}

@Composable
private fun DisableOverscroll(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalOverscrollConfiguration provides null, content = content)
}
