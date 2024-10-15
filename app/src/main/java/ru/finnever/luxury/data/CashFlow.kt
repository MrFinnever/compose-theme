package ru.finnever.luxury.data

data class CashFlow(
    val income: Double = 0.0,
    val expenses: Double = 0.0
)

val monthCashFlow = CashFlow(156723.15, 68934.72)
val quarterCashFlow = CashFlow(470169.45, 236804.16)
val yearCashFlow = CashFlow(1880677.8, 947216.64)
