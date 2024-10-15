package ru.finnever.luxury

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.finnever.luxury.viewmodel.DarkModeViewModel
import ru.finnever.luxury.screens.main.MainScreen
import ru.finnever.luxury.ui.theme.LuxuryTheme
import ru.finnever.luxury.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = viewModel<MainViewModel>()
            val state by viewModel.uiState.collectAsState()


            val darkModeViewModel = viewModel<DarkModeViewModel>()
            if (darkModeViewModel.isDarkMode == null || darkModeViewModel.asInSystemMode)
                darkModeViewModel.isDarkMode = isSystemInDarkTheme()

            LuxuryTheme(
                darkTheme = state.isDarkMode
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LuxuryTheme.colors.background
                ) {

                    MainScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
