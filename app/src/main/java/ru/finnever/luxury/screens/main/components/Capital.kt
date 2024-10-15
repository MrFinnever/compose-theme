package ru.finnever.luxury.screens.main.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.finnever.luxury.R
import ru.finnever.luxury.data.sizeOfCapital
import ru.finnever.luxury.ui.theme.LuxuryTheme
import ru.finnever.luxury.viewmodel.MainViewModel
import ru.finnever.luxury.viewmodel.PeriodViewModel
import java.text.NumberFormat


@Composable
fun Capital(
    modifier: Modifier
) {
    val TESTviewModel = viewModel<MainViewModel>()
    val state by TESTviewModel.uiState.collectAsState()

    val periodViewModel = viewModel<PeriodViewModel>()


    val increasePercent = when(periodViewModel.capitalPeriod) {
        "за месяц" -> (sizeOfCapital.current / sizeOfCapital.lastMonth) - 1
        "за квартал" -> (sizeOfCapital.current / sizeOfCapital.lastQuarter) - 1
        else -> (sizeOfCapital.current / sizeOfCapital.lastYear) - 1
    }

    Box(
        modifier = modifier
    ) {
        Column {

            val vertClickTextPadding = 3.dp
            val endClickTextPadding = 5.dp

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = LuxuryTheme.shapes.innerPadding - vertClickTextPadding)
            ) {

                Text(
                    text = "Капитал",
                    color = LuxuryTheme.colors.primaryText,
                    fontSize = LuxuryTheme.text.title.fontSize,
                    fontWeight = LuxuryTheme.text.title.fontWeight,
                    modifier = Modifier.padding(start = LuxuryTheme.shapes.innerPadding)
                )
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .padding(horizontal = endClickTextPadding)
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
                            TESTviewModel.switchCapitalPeriod()
                        }
                ) {
                    //TODO("РЕАЛИЗОВАТЬ MVI")

                    Text(
                        text = periodViewModel.capitalPeriod,
                        color = LuxuryTheme.colors.secondaryText,
                        fontSize = LuxuryTheme.text.title.fontSize,
                        fontWeight = LuxuryTheme.text.title.fontWeight,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .padding(
                                horizontal = LuxuryTheme.shapes.innerPadding - endClickTextPadding,
                                vertical = vertClickTextPadding)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(LuxuryTheme.colors.secondarySurface)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .fillMaxWidth()
                ) {
                    LineCapital(increasePercent = increasePercent)

                    LinePercent(increasePercent = increasePercent)
                }
            }

        }
    }
}




@Composable
private fun LineCapital(
    increasePercent: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter =
                if (increasePercent > 0) painterResource(id = R.drawable.icon_balance)
                else painterResource(id = R.drawable.icon_balance),
                contentDescription = "Income",
                tint = LuxuryTheme.colors.onPrimary
            )
        }

        val rubleFormat = NumberFormat.getCurrencyInstance()


        Text(
            text = rubleFormat.format(sizeOfCapital.current),
            color = LuxuryTheme.colors.onPrimary,
            fontSize = LuxuryTheme.text.body.fontSize,
            fontWeight = LuxuryTheme.text.title.fontWeight,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Composable
private fun LinePercent(
    increasePercent: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter =
                if (increasePercent > 0) painterResource(id = R.drawable.icon_trend_up)
                else painterResource(id = R.drawable.icon_trend_down),
                contentDescription = "Income",
                tint = when {
                    (increasePercent > 0) -> Color.Green
                    (increasePercent < 0) -> Color.Red
                    else -> Color.Black
                }
            )
        }

        val percentFormat = NumberFormat.getPercentInstance()
        percentFormat.maximumFractionDigits = 2

        Text(
            text = percentFormat.format(increasePercent),
            color = LuxuryTheme.colors.onPrimary,
            fontSize = LuxuryTheme.text.body.fontSize,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CapitalPreview() {
    LuxuryTheme {
        Box(modifier = Modifier
            .background(LuxuryTheme.colors.background)
            .padding(10.dp)
        )
        {
            Capital(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(LuxuryTheme.shapes.cornersStyle)
                    .background(LuxuryTheme.colors.surface)
            )
        }
    }

}