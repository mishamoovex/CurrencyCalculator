package com.mykhailo.vasylenko.app.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var suspendSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().setKeepOnScreenCondition { suspendSplashScreen }
        super.onCreate(savedInstanceState)

        setContent {
            ApplicationTheme {
                MainScreenEntry(
                    viewModel = hiltViewModel(),
                    hideSplashScreen = { suspendSplashScreen = false },
                )
            }
        }
    }
}
