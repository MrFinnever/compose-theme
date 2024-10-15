package ru.finnever.luxury.data

data class Capital(
    val current: Double,
    val lastMonth: Double,
    val lastQuarter: Double,
    val lastYear: Double
)
val sizeOfCapital = Capital(3412556.21, 3596362.98, 2897113.28, 2988120.05)

