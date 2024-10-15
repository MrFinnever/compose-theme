package ru.finnever.luxury.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import ru.finnever.luxury.R


//==================================================//
//                     App Theme                    //
//==================================================//
@Composable
fun LuxuryTheme(
    textSize: LuxurySize = LuxurySize.Medium,
    innerPadding: LuxurySize = LuxurySize.Medium,
    outerPadding: LuxurySize = LuxurySize.Medium,
    corners: LuxuryCorners = LuxuryCorners.SuperRounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    //==================================================//
    //                   Color scheme                   //
    //==================================================//
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    //==================================================//
    //                       Text                       //
    //==================================================//
    val text = LuxuryThemeText(
        bigTitle = TextStyle(
            fontSize = when (textSize) {
                LuxurySize.Small -> 28.sp
                LuxurySize.Medium -> 32.sp
                LuxurySize.Big -> 34.sp
            },
            fontWeight = FontWeight.Bold
        ),
        title = TextStyle(
            fontSize = when (textSize) {
                LuxurySize.Small -> 18.sp
                LuxurySize.Medium -> 20.sp
                LuxurySize.Big -> 24.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                LuxurySize.Small -> 16.sp
                LuxurySize.Medium -> 18.sp
                LuxurySize.Big -> 20.sp
            },
            fontWeight = FontWeight.Normal
        )
    )

    //==================================================//
    //                      Shapes                      //
    //==================================================//
    val shapes = LuxuryThemeShape(
        innerPadding = when (innerPadding) {
            LuxurySize.Small -> 10.dp
            LuxurySize.Medium -> 15.dp
            LuxurySize.Big -> 20.dp
        },
        outerPadding = when (outerPadding) {
            LuxurySize.Small -> 10.dp
            LuxurySize.Medium -> 15.dp
            LuxurySize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            LuxuryCorners.SmallRounded -> RoundedCornerShape(5.dp)
            LuxuryCorners.Rounded -> RoundedCornerShape(10.dp)
            LuxuryCorners.BigRounded -> RoundedCornerShape(15.dp)
            LuxuryCorners.SuperRounded -> RoundedCornerShape(20.dp)
            LuxuryCorners.Flat -> RoundedCornerShape(0.dp)
        }
    )


    //==================================================//
    //                       Icon                       //
    //==================================================//
    val icons = LuxuryThemeIcon(
        icon =  if (darkTheme) R.drawable.icon_dark_mode else R.drawable.icon_light_mode,
        iconDescription = if (darkTheme) "Dark mode" else "Light mode"
    )

    //==================================================//
    //                  StatusBarColor                  //
    //==================================================//
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }



    //==================================================//
    //            Uploading the Material Theme          //
    //==================================================//
     CompositionLocalProvider(
        LocalLuxuryColors provides colorScheme,
        LocalLuxuryText provides text,
        LocalLuxuryShapes provides shapes,
        LocalLuxuryIcon provides icons,
        content = content
    )
}