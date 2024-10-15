package ru.finnever.luxury.ui.theme

import androidx.compose.ui.graphics.Color


//=================================================================================================//

            //==================================================//
            // ▁ ▂ ▃ ▅ ▆ █       Color Palettes     █ ▆ ▅ ▃ ▂ ▁ //
            //==================================================//

//==================================================//
//                   Dark Colors                    //
//==================================================//
val Purple80 = Color(0xFFC3BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Dark_Primary = Color(0xFF2F92FF)
val Dark_Secondary = Color(0xFF34AADC)
//val Dark_Tertiary = Color(0xFF5AC8FA)

val Dark_Background = Color(0xFF0A0A0A)
val Dark_Surface = Color(0xFF2A2A2C)
val Dark_SecondarySurface = Color(0xFF444446)

/*
val Dark_Background = Color(0xFF0A0A0A)
val Dark_Surface = Color(0xFF3A3A3A)
val Dark_SecondarySurface = Color(0xFF636363)
 */

val Dark_PrimaryText = Color(0xFFFFFFFF)
val Dark_SecondaryText = Color(0xFFC9C9C9)
val Dark_OnPrimary = Color(0xFFFFFFFF)

val Dark_Tint = Color(0xFFFFFFFF)
val Dark_TintGood = Color(0xB331FF31)
val Dark_TintBad = Color(0xB3FF1818)

val Dark_SkyLight = Color(0xffbdd0e4)

//==================================================//
//                   Light colors                   //
//==================================================//
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Light_Primary = Color(0xFF007AFF)
val Light_Secondary = Color(0xFF34AADC)
//val Light_Tertiary = Color(0xFF5AC8FA)

val Light_Background = Color(0xFFD8DCE0)
val Light_Surface = Color(0xFFFFFFFF)
val Light_SecondarySurface = Color(0xFF353535)

val Light_PrimaryText = Color(0xFF000000)
val Light_SecondaryText = Color(0xFFB6B6B6)
val Light_OnPrimary = Color(0xFFFFFFFF)

val Light_Tint = Color(0xFF000000)
val Light_TintGood = Color(0xB300FF00)
val Light_TintBad = Color(0xB3FF0000)

val Light_SkyLight = Color(0xfffff917)

//=================================================================================================//

            //==================================================//
            // ▁ ▂ ▃ ▅ ▆ █         Mode color       █ ▆ ▅ ▃ ▂ ▁ //
            //==================================================//

//==================================================//
//                    Dark mode                     //
//==================================================//
val DarkColorScheme = LuxuryThemeColors(
    primaryColor = Dark_Primary,
    secondaryColor = Dark_Secondary,

    background = Dark_Background,
    surface = Dark_Surface,
    secondarySurface = Dark_SecondarySurface,

    primaryText = Dark_PrimaryText,
    secondaryText = Dark_SecondaryText,
    onPrimary = Dark_OnPrimary,

    tintColor = Dark_Tint,
    tintColorGood = Dark_TintGood,
    tintColorBad = Dark_TintBad,

    skyLight = Dark_SkyLight
)



//==================================================//
//                    Light mode                    //
//==================================================//
val LightColorScheme = LuxuryThemeColors(
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








