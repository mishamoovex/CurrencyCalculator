package com.mykhailo.vasylenko.designsytem.theme.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

private val PlummyDark = Color(0xFF633347)
private val DeepRose = Color(0xFFB75784)
private val Rose = Color(0xFFC9A7B5)


internal val DarkColorPalette = ApplicationColors(

)


internal val DarkTextSelectionColor = TextSelectionColors(
    handleColor = DeepRose,
    backgroundColor = Rose
)

@Immutable
internal object MamaTalkRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = RippleTheme.defaultRippleColor(
        contentColor = PlummyDark,
        lightTheme = !isSystemInDarkTheme()
    )

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = PlummyDark,
        lightTheme = !isSystemInDarkTheme()
    )

}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [ApplicationTheme.colors].
 */
internal fun debugColors(
    darkTheme: Boolean = false,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)
