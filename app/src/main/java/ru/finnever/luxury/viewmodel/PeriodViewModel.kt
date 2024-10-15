package ru.finnever.luxury.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PeriodViewModel : ViewModel() {
    var capitalPeriod by mutableStateOf("за год")
    var cashFlowPeriod by mutableStateOf("за месяц")

    private var clickedBalance: Int = 0
    private var clickedCashFlow: Int = 0

    fun changeCapitalPeriod() {
        clickedBalance++
        capitalPeriod = when (clickedBalance) {
            1 -> "за месяц"
            2 -> "за квартал"
            else -> "за год"
        }
        if (clickedBalance == 3) clickedBalance = 0
    }
    fun changeCashFlowPeriod() {
        clickedCashFlow++
        cashFlowPeriod = when (clickedCashFlow) {
            1 -> "за квартал"
            2 -> "за год"
            else -> "за месяц"
        }
        if (clickedCashFlow == 3) clickedCashFlow = 0
    }
}
