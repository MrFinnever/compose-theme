package ru.finnever.luxury.screens.main.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.finnever.luxury.R
import ru.finnever.luxury.ui.theme.LuxuryTheme
import ru.finnever.luxury.viewmodel.DarkModeViewModel

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
) {
    val darkModeViewModel = viewModel<DarkModeViewModel>()
    val isSystemInDarkMode = isSystemInDarkTheme()

    Box (
        modifier = modifier.padding(start = 15.dp, end = 10.dp, top = 15.dp, bottom = 5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Баланс",
                color = LuxuryTheme.colors.primaryText,
                fontSize = LuxuryTheme.text.bigTitle.fontSize,
                fontWeight = LuxuryTheme.text.bigTitle.fontWeight,
            )
            IconButton(
                onClick = {
                    darkModeViewModel.asInSystemMode = true
                    darkModeViewModel.isDarkMode = isSystemInDarkMode },
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_auto_light),
                    contentDescription = LuxuryTheme.icons.iconDescription,
                    tint = if (darkModeViewModel.asInSystemMode) LuxuryTheme.colors.skyLight
                        else LuxuryTheme.colors.tintColor,
                    modifier = Modifier.size(size = 32.dp)
                )
            }
            IconButton(
                onClick = {
                    darkModeViewModel.asInSystemMode = false
                    darkModeViewModel.switchDarkMode()},
            ) {
                Icon(
                    painter = painterResource(id = LuxuryTheme.icons.icon),
                    contentDescription = LuxuryTheme.icons.iconDescription,
                    tint = if (darkModeViewModel.asInSystemMode) LuxuryTheme.colors.tintColor
                    else LuxuryTheme.colors.skyLight,
                    modifier = Modifier.size(size = 32.dp)
                )
            }
        }

        //TODO("ТЕКСТ С СУММОЙ ВСЕГО КАПИТАЛА")

    }
}



@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    LuxuryTheme {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(LuxuryTheme.colors.background)
        )
    }
}