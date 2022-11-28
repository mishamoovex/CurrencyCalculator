package com.mykhailo.vasylenko.app.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph

/**
 * Send result back using the [NavBackStackEntry] and [SavedStateHandle].
 *
 * By default it sends the [data] to the previous destination on the navigation back stack using
 * the [key] or do nothing if less than two destinations on the stack.
 *
 * NOTE: if we're want to pop back stack to some arbitrary destination after sending result
 * (not to the previous one) we should find that destination [NavBackStackEntry] because
 * getPreviousBackStackEntry will not send result to the right destination.
 *
 * @param arbitraryDestination any destination in the [NavGraph] to which we want send data back
 * @param key the key to receive data via result callbacks
 * @param data the result data to send to the caller
 */
fun <T> NavController.sendNavigationResult(
    key: String,
    data: T,
    arbitraryDestination: String? = null
) {
    //fetch backStackEntry as per the data destination
    val backStackEntry =
        if (arbitraryDestination != null) {
            getBackStackEntry(arbitraryDestination)
        } else {
            previousBackStackEntry
        }

    backStackEntry
        ?.savedStateHandle
        ?.set(key, data)
}

@Composable
fun <T> NavBackStackEntry.registerForResult(key: String): T? =
    savedStateHandle
        .getLiveData<T>(key)
        .observeAsState()
        .value


