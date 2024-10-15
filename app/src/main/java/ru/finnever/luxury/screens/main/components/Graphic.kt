package ru.finnever.luxury.screens.main.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import ru.finnever.luxury.ui.theme.LuxuryTheme

@Composable
fun CircularI(
    canvasSize: Dp = 300.dp,
    indicatorValue: Int = 100,
    maxIndicatorValue: Int = 100,
    indicatorStrokeWidth: Float = 100f
) {

    val animatedIndicatorValue = remember {
        Animatable(initialValue = indicatorValue.toFloat())
    }
    LaunchedEffect(key1 = indicatorValue) {
        animatedIndicatorValue.animateTo(indicatorValue.toFloat())
    }

    val percentage = (animatedIndicatorValue.value / maxIndicatorValue) * 100
    val indicatorSweepAngle by animateFloatAsState(
        targetValue = (3.6 * percentage).toFloat(),
        animationSpec = tween(1000), label = ""
    )
    val color = LuxuryTheme.colors.tintColorBad
    Canvas(
        modifier = Modifier
            .size(canvasSize),
        onDraw = {
            val componentSize = size / 1.25f
            indicator(
                sweepAngle = indicatorSweepAngle,
                componentSize = componentSize,
                indicatorColor = color,
                indicatorStrokeWidth = indicatorStrokeWidth
            )

        }
    )
}



private fun DrawScope.indicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    rotate(degrees = 270f) {
        drawArc(
            size = componentSize,
            color = indicatorColor,
            startAngle = 0f,
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




@Preview(showBackground = true)
@Composable
fun CircularIndicatorPreview() {
    CircularI()
}

private fun dynamicColor(sweepAngle: Float) : Color {
    return when {
        (sweepAngle < 90f) -> Color(red = 1f, green = 0f, blue = 0f, alpha = 0.9f)
        (sweepAngle < 180f) -> Color(red = 1f, green = 0.5f, blue = 0f, alpha = 0.9f)
        (sweepAngle < 270f) -> Color(red = 1f, green = 1f, blue = 0f, alpha = 0.9f)
        else -> Color(red = 0f, green = 1f, blue = 0f, alpha = 0.9f)
    }
}
