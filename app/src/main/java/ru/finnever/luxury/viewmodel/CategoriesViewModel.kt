package ru.finnever.luxury.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CategoriesViewModel: ViewModel() {
    var switcher by mutableStateOf("Расходы")
    var randomDegrees by mutableStateOf(Random.nextDouble(20.0, 320.0).toFloat())

    private var clickedDonutPieChart: Int = 0

    fun switchDonutPieChart() {
        clickedDonutPieChart++
        switcher = when (clickedDonutPieChart) {
            1 -> "Доходы"
            else -> "Расходы"
        }
        if (clickedDonutPieChart == 2) clickedDonutPieChart = 0
    }
}