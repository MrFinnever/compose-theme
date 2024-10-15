package ru.finnever.luxury.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.finnever.luxury.data.MainScreenState

class MainViewModel : ViewModel() {
    private val mainScreenState = MainScreenState (
        isAsSystemDarkMode = true,
        isDarkMode = false,
        balancePeriod = "за год",
        cashFlowPeriod = ""
    )
    private val stateFlow = MutableStateFlow(mainScreenState)
    val uiState: StateFlow<MainScreenState> = stateFlow.asStateFlow()


    private var clickedBalance: Int = 0
    private var clickedCashFlow: Int = 0

    fun switchCapitalPeriod() {
        clickedBalance++
        uiState.value.cashFlowPeriod = when (clickedBalance) {
            1 -> "за месяц"
            2 -> "за квартал"
            else -> "за год"
        }
        if (clickedBalance == 3) clickedBalance = 0
    }

    fun changeCashFlowPeriod() {
        clickedCashFlow++
        uiState.value.cashFlowPeriod = when (clickedCashFlow) {
            1 -> "за квартал"
            2 -> "за год"
            else -> "за месяц"
        }
        if (clickedCashFlow == 3) clickedCashFlow = 0
    }
}