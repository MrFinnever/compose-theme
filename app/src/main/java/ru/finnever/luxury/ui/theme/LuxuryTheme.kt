package ru.finnever.luxury.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.finnever.luxury.R

//=================================================================================================//

            //==================================================//
            // ▁ ▂ ▃ ▅ ▆ █  Params of Luxury Theme  █ ▆ ▅ ▃ ▂ ▁ //
            //==================================================//

//==================================================//
//                       Color                      //
//==================================================//
data class LuxuryThemeColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val background: Color,
    val surface: Color,
    val secondarySurface: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val onPrimary: Color,
    val tintColor: Color,
    val tintColorGood: Color,
    val tintColorBad: Color,
    val skyLight: Color
)

//==================================================//
//                       Text                       //
//==================================================//
data class LuxuryThemeText(
    val bigTitle: TextStyle,
    val title: TextStyle,
    val body: TextStyle
)

//==================================================//
//                       Shape                      //
//==================================================//
data class LuxuryThemeShape(
    val innerPadding: Dp,
    val outerPadding: Dp,
    val cornersStyle: Shape
)

//==================================================//
//                       Icon                       //
//==================================================//
data class LuxuryThemeIcon(
    val icon: Int,
    val iconDescription: String
)

//=================================================================================================//

            //==================================================//
            // ▁ ▂ ▃ ▅ ▆ █     Creating Subtypes    █ ▆ ▅ ▃ ▂ ▁ //
            //==================================================//

//==================================================//
//               Size (Text or Paddings)            //
//==================================================//
enum class LuxurySize {
    Small, Medium, Big
}

//==================================================//
//                      Corners                     //
//==================================================//
enum class LuxuryCorners {
    SmallRounded, Rounded, BigRounded, SuperRounded, Flat
}


//=================================================================================================//

            //==================================================//
            // ▁ ▂ ▃ ▅ ▆ █       Luxury Theme       █ ▆ ▅ ▃ ▂ ▁ //
            //==================================================//

object LuxuryTheme {
    internal val colors: LuxuryThemeColors
        @Composable get() = LocalLuxuryColors.current

    internal val text: LuxuryThemeText
        @Composable get() = LocalLuxuryText.current

    internal val shapes: LuxuryThemeShape
        @Composable get() = LocalLuxuryShapes.current

    internal val icons: LuxuryThemeIcon
        @Composable get() = LocalLuxuryIcon.current
}
//=================================================================================================//

            //==================================================//
            // ▁ ▂ ▃ ▅ ▆ █    Default Composition   █ ▆ ▅ ▃ ▂ ▁ //
            //==================================================//

internal val LocalLuxuryColors = staticCompositionLocalOf {
    LuxuryThemeColors(
        primaryColor = Light_Primary,
        secondaryColor = Light_Secondary,

        background = Light_Background,
        surface = Light_Surface,
        secondarySurface = Light_SecondarySurface,

        primaryText = Light_PrimaryText,
        secondaryText = Light_SecondaryText,
        onPrimary = Light_OnPrimary,

        tintColor = Light_Tint,
        tintColorGood = Light_TintGood,
        tintColorBad = Light_TintBad,

        skyLight = Light_SkyLight
    )
}

internal val LocalLuxuryText = staticCompositionLocalOf {
    LuxuryThemeText(
        bigTitle = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        ),
        title = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    )
}

internal val LocalLuxuryShapes = staticCompositionLocalOf {
    LuxuryThemeShape(
        innerPadding = 15.dp,
        outerPadding = 15.dp,
        cornersStyle = RoundedCornerShape(15.dp)
    )
}

internal val LocalLuxuryIcon = staticCompositionLocalOf {
    LuxuryThemeIcon(
        icon = R.drawable.icon_light_mode,
        iconDescription = "Light mode"
    )
}