package ru.finnever.luxury.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DarkModeViewModel: ViewModel() {

    var asInSystemMode : Boolean by mutableStateOf(true)
    var isDarkMode: Boolean? by mutableStateOf(null)

    fun switchDarkMode() {
        if (!asInSystemMode) isDarkMode = !(isDarkMode)!!
    }
}