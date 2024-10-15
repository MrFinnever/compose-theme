package ru.finnever.luxury.data

import androidx.compose.ui.graphics.Color

data class CategoriesData(
    var categories: MutableMap<String, Double>
)

val categoriesOfExpenses = CategoriesData(
    mutableMapOf(
        "Еда" to 13570.33,
        "Квартира" to 25000.0,
        "Одежда" to 3999.0,
        "Связь" to 700.0,
        "Аптека" to 357.0,
        "Транспорт" to 3760.0
    )
)


val categoriesOfIncomes = CategoriesData(
    mutableMapOf(
        "Зарплата" to 75000.0,
        "Дивиденды" to 5436.99,
        "Купоны" to 1123.12,
        "Краудлендинг" to 733.6,
        "Кэшбэк" to 278.0,
        "Стипендия" to 1057.0
    )
)


//========== Colors ==========//
val colorOfCategory = listOf(
    Color(0xFF00B0FF),
    Color(0xFFFF0000),
    Color(0xFF71CC00),
    Color(0xFFFFD200),
    Color(0xFF00FFE7),
    Color(0xFF7F9AE7),
    Color(0xFFFB7907),
    Color(0xFF5C6D6A),
    Color(0xFFC265B2),
    Color(0xFF7772D3)
)