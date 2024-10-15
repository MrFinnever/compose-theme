package ru.finnever.luxury.screens.main.components

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.finnever.luxury.data.categoriesOfExpenses
import ru.finnever.luxury.data.categoriesOfIncomes
import ru.finnever.luxury.data.colorOfCategory
import ru.finnever.luxury.ui.theme.LuxuryTheme
import ru.finnever.luxury.viewmodel.CategoriesViewModel
import kotlin.random.Random


@Composable
fun Categoties(
    modifier: Modifier
) {
   Surface(
       color = LuxuryTheme.colors.surface,
       shape = LuxuryTheme.shapes.cornersStyle,
       modifier = modifier
   ) {

       val viewModel = viewModel<CategoriesViewModel>()

       Column(
           modifier = Modifier.padding(LuxuryTheme.shapes.innerPadding)
       ) {
           Row(
               modifier = Modifier.fillMaxWidth()
           ) {
               Column {
                   Text(
                       text = "Категории",
                       color = LuxuryTheme.colors.primaryText,
                       fontSize = LuxuryTheme.text.title.fontSize,
                       fontWeight = LuxuryTheme.text.title.fontWeight,
                       modifier = Modifier.padding(start = 10.dp)
                   )
                   //Today - Week - Month - Year - All_Time
               }
               Box {
                   //Text Click
               }
           }
           Row(
               horizontalArrangement = Arrangement.SpaceBetween,
               modifier = Modifier.fillMaxWidth()
           ) {
               val interactionSource = remember { MutableInteractionSource() }
               //Chart of (Expenses or Incomes)

               Box(
                   contentAlignment = Alignment.Center,
                   modifier = Modifier
                       .padding(10.dp)
                       .clickable(
                           interactionSource = interactionSource,
                           indication = null
                       ) {
                           viewModel.switchDonutPieChart()
                       }
               ){
                    Box (
                        modifier = Modifier
                        .rotate(viewModel.randomDegrees)
                        ) {
                            DonutPieChart(switcher = viewModel.switcher)
                    }


                   Text(
                       text = viewModel.switcher,
                       color = LuxuryTheme.colors.primaryText,
                       fontSize = LuxuryTheme.text.body.fontSize,
                       fontWeight = LuxuryTheme.text.body.fontWeight
                   )
               }

               Column {
                   Text(
                       text = "Категория №1",
                       color = LuxuryTheme.colors.primaryText,
                       fontSize = LuxuryTheme.text.body.fontSize,
                       fontWeight = LuxuryTheme.text.body.fontWeight,
                       modifier = Modifier.padding(vertical = 10.dp)
                   )

                   Text(
                       text = "Категория №2",
                       color = LuxuryTheme.colors.primaryText,
                       fontSize = LuxuryTheme.text.body.fontSize,
                       fontWeight = LuxuryTheme.text.body.fontWeight,
                       modifier = Modifier.padding(vertical = 10.dp)
                   )
                   Text(
                       text = "Категория №3",
                       color = LuxuryTheme.colors.primaryText,
                       fontSize = LuxuryTheme.text.body.fontSize,
                       fontWeight = LuxuryTheme.text.body.fontWeight,
                       modifier = Modifier.padding(vertical = 10.dp)
                   )
               }

           }
       }


   }
}


@Composable
fun DonutPieChart(
    switcher: String
) {
    var sumOfExpenses = 0.0
    val ratioOfExpenses = mutableListOf<Double>()
    val valuesOfExpenses = mutableListOf<Double>()

    if (switcher == "Расходы") {
        categoriesOfExpenses.categories.forEach { (_, value: Double) ->
            sumOfExpenses += value
        }
        categoriesOfExpenses.categories.forEach { (_, value: Double) ->
            ratioOfExpenses += value / sumOfExpenses
            valuesOfExpenses += value
        }
    }


    var sumOfIncomes = 0.0
    val ratioOfIncomes = mutableListOf<Double>()
    val valuesOfIncomes = mutableListOf<Double>()

    if (switcher == "Доходы") {
        categoriesOfIncomes.categories.forEach { (_, value: Double) ->
            sumOfIncomes += value
        }
        categoriesOfIncomes.categories.forEach { (_, value: Double) ->
            ratioOfIncomes += value / sumOfExpenses
            valuesOfIncomes += value
        }
    }

    //========== Expenses ==========//
    var currentAngle = 0.0
    var indexOfColor = 0
    for (pie in valuesOfExpenses) {
        CircularIndicator(
            color =
            if (valuesOfExpenses.size > colorOfCategory.size) {
                if (indexOfColor > colorOfCategory.lastIndex) indexOfColor = 0
                colorOfCategory[indexOfColor]
            }
            else
                colorOfCategory[indexOfColor],
            indicatorValue = pie.toFloat(),
            maxIndicatorValue = sumOfExpenses,
            startAngle = currentAngle.toFloat(),
            indicatorStrokeWidth = 50f
        )
        indexOfColor++
        currentAngle += ratioOfExpenses[valuesOfExpenses.indexOf(pie)] * 360
    }

    currentAngle = 0.0
    //========== Incomes ==========//
    for (pie in valuesOfIncomes) {
        currentAngle += ratioOfIncomes[valuesOfIncomes.indexOf(pie)] * 360
        CircularIndicator(
            color = Color(Random.nextInt()),
            indicatorValue = pie.toFloat(),
            maxIndicatorValue = sumOfIncomes,
            startAngle = currentAngle.toFloat(),
            indicatorStrokeWidth = 50f
        )
    }
}



@Composable
fun CircularIndicator(
    canvasSize: Dp = 200.dp,
    color: Color,
    indicatorValue: Float,
    maxIndicatorValue: Double,
    startAngle: Float,
    indicatorStrokeWidth: Float
) {

    // For Animation
    val animatedIndicatorValue = remember {
        Animatable(initialValue = indicatorValue.toFloat())
    }
    LaunchedEffect(key1 = indicatorValue) {
        animatedIndicatorValue.animateTo(indicatorValue.toFloat())
    }

    // Calculation the size of Angle
    val percentage = (animatedIndicatorValue.value / maxIndicatorValue) * 100
    val indicatorSweepAngle by animateFloatAsState(
        targetValue = (3.6 * percentage).toFloat(),
        animationSpec = tween(1000), label = ""
    )

    val sizeOfSeparator = 1.5f
    val colorOfSeparator = LuxuryTheme.colors.surface
    Canvas(
        modifier = Modifier
            .size(canvasSize),
        onDraw = {
            val componentSize = size / 1.25f
            indicator(
                sweepAngle = indicatorSweepAngle,
                startAngle = startAngle,
                componentSize = componentSize,
                indicatorColor = color,
                indicatorStrokeWidth = indicatorStrokeWidth
            )


            separator(
                startAngle = (startAngle + indicatorSweepAngle - sizeOfSeparator),
                sizeOfSeparator = sizeOfSeparator,
                componentSize = componentSize,
                indicatorColor = colorOfSeparator,
                indicatorStrokeWidth = indicatorStrokeWidth
            )

        }
    )
}



private fun DrawScope.indicator(
    sweepAngle: Float,
    startAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {

    rotate(degrees = 270f) {
        drawArc(
            size = componentSize,
            color = indicatorColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(
                width = indicatorStrokeWidth,
                cap = StrokeCap.Butt,
            ),
            topLeft = Offset(
                x = (size.width - componentSize.width) / 2f,
                y = (size.height - componentSize.height) / 2f
            )
        )
    }
}

private fun DrawScope.separator(
    startAngle: Float,
    sizeOfSeparator: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {

    rotate(degrees = 270f) {
        drawArc(
            size = componentSize,
            color = indicatorColor,
            startAngle = startAngle,
            sweepAngle = sizeOfSeparator,
            useCenter = false,
            style = Stroke(
                width = indicatorStrokeWidth,
                cap = StrokeCap.Butt,
            ),
            topLeft = Offset(
                x = (size.width - componentSize.width) / 2f,
                y = (size.height - componentSize.height) / 2f
            )
        )
    }
}



@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CategoriesPreview() {
    //val viewModel = viewModel<CategoriesViewModel>()
    LuxuryTheme {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(LuxuryTheme.colors.background)
            .padding(15.dp)

        ) {
            Categoties(modifier = Modifier.fillMaxWidth())
        }
    }

}