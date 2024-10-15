package ru.finnever.luxury.screens.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.finnever.luxury.screens.main.components.Capital
import ru.finnever.luxury.screens.main.components.CashFlow
import ru.finnever.luxury.screens.main.components.Categoties
import ru.finnever.luxury.screens.main.components.TopBar
import ru.finnever.luxury.ui.theme.LuxuryTheme


@Composable
fun MainScreen(
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            TopBar(
                modifier = Modifier.fillMaxWidth())
            Capital(
                modifier = Modifier
                    .padding(horizontal = LuxuryTheme.shapes.outerPadding)
                    .padding(top = 5.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .clip(LuxuryTheme.shapes.cornersStyle)
                    .background(LuxuryTheme.colors.surface)
            )
            CashFlow(
                modifier = Modifier
                    .padding(horizontal = LuxuryTheme.shapes.outerPadding)
                    .padding(top = 5.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .clip(LuxuryTheme.shapes.cornersStyle)
                    .background(LuxuryTheme.colors.surface)
            )
            Categoties(modifier = Modifier
                .padding(horizontal = LuxuryTheme.shapes.outerPadding)
                .padding(top = 5.dp, bottom = 10.dp)
                .fillMaxSize()
            )
        }
    }
}



@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    LuxuryTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(LuxuryTheme.colors.background)
        ) {
            MainScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }

}

