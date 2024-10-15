package ru.finnever.luxury.screens.main.components

import android.content.res.Configuration
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.finnever.luxury.R
import ru.finnever.luxury.data.monthCashFlow
import ru.finnever.luxury.data.quarterCashFlow
import ru.finnever.luxury.data.yearCashFlow
import ru.finnever.luxury.ui.theme.LuxuryTheme
import ru.finnever.luxury.viewmodel.PeriodViewModel
import java.text.NumberFormat

@Composable
fun CashFlow(
    modifier: Modifier
) {
    val periodViewModel = viewModel<PeriodViewModel>()
    val vertClickTextPadding = 3.dp
    val endClickTextPadding = 5.dp

    Surface(
        color = LuxuryTheme.colors.surface,
        shape = LuxuryTheme.shapes.cornersStyle,
        modifier = modifier
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = LuxuryTheme.shapes.innerPadding - vertClickTextPadding)
            ) {
                Text(
                    text = "Денежный поток",
                    color = LuxuryTheme.colors.primaryText,
                    fontSize = LuxuryTheme.text.title.fontSize,
                    fontWeight = LuxuryTheme.text.title.fontWeight,
                    modifier = Modifier.padding(start = LuxuryTheme.shapes.innerPadding)
                )

                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .padding(horizontal = endClickTextPadding)
                        .clip(LuxuryTheme.shapes.cornersStyle)
                        .clickable {
                            periodViewModel.changeCashFlowPeriod()
                        }
                ) {
                    Text(
                        text = periodViewModel.cashFlowPeriod,
                        color = LuxuryTheme.colors.secondaryText,
                        fontSize = LuxuryTheme.text.title.fontSize,
                        fontWeight = LuxuryTheme.text.title.fontWeight,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .padding(
                                horizontal = LuxuryTheme.shapes.innerPadding - endClickTextPadding,
                                vertical = vertClickTextPadding
                            )
                    )
                }


            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = LuxuryTheme.shapes.innerPadding)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_right_up),
                            contentDescription = "Income",
                            tint = LuxuryTheme.colors.tintColorGood
                        )

                    }
                    Text(
                        text = "Доходы",
                        color = LuxuryTheme.colors.primaryText,
                        fontSize = LuxuryTheme.text.body.fontSize,
                        fontWeight = LuxuryTheme.text.body.fontWeight
                    )
                }


                LineIncome(periodViewModel.cashFlowPeriod)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = LuxuryTheme.shapes.innerPadding)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .size(30.dp)
                            .rotate(180f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_right_up),
                            contentDescription = "Expenses",
                            tint = LuxuryTheme.colors.tintColorBad
                        )

                    }
                    Text(
                        text = "Расходы",
                        color = LuxuryTheme.colors.primaryText,
                        fontSize = LuxuryTheme.text.body.fontSize,
                        fontWeight = LuxuryTheme.text.body.fontWeight
                    )
                }
                LineExpenses(periodViewModel.cashFlowPeriod)
            }



            val ratio = when(periodViewModel.cashFlowPeriod) {
                "за месяц" -> monthCashFlow.income /
                        (monthCashFlow.income + monthCashFlow.expenses)
                "за квартал" -> quarterCashFlow.income /
                        (quarterCashFlow.income + quarterCashFlow.expenses)
                else -> yearCashFlow.income /
                        (yearCashFlow.income + yearCashFlow.expenses)
            }
            val color = LuxuryTheme.colors.surface
            /*Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LuxuryTheme.shapes.innerPadding),
                onDraw =  {
                    incomeIndicator(ratio)
                    expensesIndicator(ratio)
                    profitIndicator(ratio, color)
                }
            )

             */

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .padding(top = 0.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(LuxuryTheme.colors.secondarySurface)
            ) {
                LineProfit(periodViewModel.cashFlowPeriod)
            }

        }
    }
}



@Composable
fun LineIncome(
    period: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){


        val rubleFormat = NumberFormat.getInstance()


        Text(
            text = when(period) {
                "за месяц" -> rubleFormat.format(monthCashFlow.income)
                "за квартал" -> rubleFormat.format(quarterCashFlow.income)
                else -> rubleFormat.format(yearCashFlow.income)
            },
            color = LuxuryTheme.colors.primaryText,
            fontSize = LuxuryTheme.text.body.fontSize,
            fontWeight = LuxuryTheme.text.body.fontWeight
        )
        /*Box(
                contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.size(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_up),
                contentDescription = "Income",
                tint = Color.Green
            )
        }
         */
    }
}

@Composable
fun LineExpenses(
    period: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        val rubleFormat = NumberFormat.getInstance()

        Text(
            text = when(period) {
                "за месяц" -> rubleFormat.format(monthCashFlow.expenses)
                "за квартал" -> rubleFormat.format(quarterCashFlow.expenses)
                else -> rubleFormat.format(yearCashFlow.expenses)
            },
            color = LuxuryTheme.colors.primaryText,
            fontSize = LuxuryTheme.text.body.fontSize,
            fontWeight = LuxuryTheme.text.body.fontWeight
        )

        /*Box(
                contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.size(30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_down),
                contentDescription = "Expenses",
                tint = Color.Red
            )
        }
         */
    }
}

@Composable
fun LineProfit(
    period: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ){
        Text(
            text = "Итого",
            color = LuxuryTheme.colors.onPrimary,
            fontSize = LuxuryTheme.text.body.fontSize,
            fontWeight = LuxuryTheme.text.title.fontWeight,
        )

        val rubleFormat = NumberFormat.getCurrencyInstance()
        val profit = when(period) {
            "за месяц" -> monthCashFlow.income - monthCashFlow.expenses
            "за квартал" -> quarterCashFlow.income - quarterCashFlow.expenses
            else -> yearCashFlow.income - yearCashFlow.expenses
        }

        Text(
            text = rubleFormat.format(profit),
            color = LuxuryTheme.colors.onPrimary,
            fontSize = LuxuryTheme.text.body.fontSize,
            fontWeight = LuxuryTheme.text.title.fontWeight,
        )
    }
}



// TODO: Функция форматирования P&L
// 10млн и 10 млн не влязают полностью с запятыми!
// Максимум: 1 000 000 000 и 100 000 000 целыми




fun DrawScope.incomeIndicator(
    ratio: Double
) {
    drawLine(
        color = Color.Green,
        start = Offset(0f, 0f),
        end = Offset((size.width * ratio).toFloat(), 0f),
        strokeWidth = 10f,
        cap = StrokeCap.Round
    )
}
fun DrawScope.expensesIndicator(
    ratio: Double
) {
    drawLine(
        color = Color.Red,
        start = Offset((size.width * ratio).toFloat(), 0f),
        end = Offset(size.width, 0f),
        strokeWidth = 10f,
        cap = StrokeCap.Round
    )
}
fun DrawScope.profitIndicator(
    ratio: Double,
    color: Color
) {
    if (ratio != 0.5) {
        drawLine(
            color = if (ratio > 0.5f) Color.Green else Color.Red,
            start = Offset((size.width * ratio).toFloat(), 0f),
            end = Offset(size.width / 2f, 0f),
            strokeWidth = 20f
        )
        drawLine(
            color = if(ratio < 0.05 || ratio > 0.95) Color.Transparent else color,
            start = if (ratio < 0.5) Offset((size.width * ratio).toFloat()-5f, -7.5f)
                    else Offset((size.width * ratio).toFloat()+5f, -7.5f),
            end = if (ratio < 0.5) Offset((size.width * ratio).toFloat()-5f, 7.5f)
            else Offset((size.width * ratio).toFloat()+5f, 7.5f),
            strokeWidth = 10f
        )
    }
    else {
        drawLine(
            color = Color.White,
            start = Offset((size.width * ratio).toFloat(), -5f),
            end = Offset((size.width * ratio).toFloat(), 5f),
            strokeWidth = 10f
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CashFlowPreview() {
    LuxuryTheme {
        Box(modifier = Modifier
            .background(LuxuryTheme.colors.background)
            .padding(10.dp)
        )
        {
            CashFlow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(LuxuryTheme.shapes.cornersStyle)
                    .background(LuxuryTheme.colors.surface),
            )
        }
    }

}