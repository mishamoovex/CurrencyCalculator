package com.mykhailo.vasylenko.app.ui.calculator.state

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.mykhailo.vasylenko.common.messaging.SnackbarManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Remembers and creates an instance of [BaseScreenState]
 */
@Composable
fun rememberBaseScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackbarManager: SnackbarManager = SnackbarManager(),
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, snackbarManager, resources, coroutineScope) {
    BaseScreenState(scaffoldState, snackbarManager, resources, coroutineScope)
}

/**
 * Responsible for holding state related to [BaseScreenState] and containing UI-related logic.
 */
@Stable
open class BaseScreenState(
    open val scaffoldState: ScaffoldState,
    open val snackbarManager: SnackbarManager,
    open val resources: Resources,
    coroutineScope: CoroutineScope
) {

    init {
        coroutineScope.launch {
            snackbarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    //we should pass formatArgs as varrarg array otherwise,
                    //the brackets [] will surround formated text
                    val text = resources.getString(message.templateRes, *message.placeholders)
                    // Display the snackbar on the screen. `showSnackbar` is a function
                    // that suspends until the snackbar disappears from the screen
                    scaffoldState.snackbarHostState.showSnackbar(text)
                    // Once the snackbar is gone or dismissed, notify the SnackbarManager
                    snackbarManager.setMessageShown(message.id)
                }
            }
        }
    }
}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
