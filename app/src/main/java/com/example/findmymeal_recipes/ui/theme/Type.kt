package com.example.findmymeal_recipes.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.findmymeal_recipes.R

val Comfortaa_light = FontFamily(
    Font(R.font.comfortaa_light)
)
val Comfortaa_semiBold = FontFamily(
    Font(R.font.comfortaa_semibold)
)

val Dancing = FontFamily(
    Font(R.font.dancing_semi_bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = Dancing,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 40.sp
    ),
    h2 = TextStyle(
        fontFamily = Comfortaa_light,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    h3 = TextStyle(
        fontFamily = Comfortaa_semiBold,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 13.sp,
    ),
    h4 = TextStyle(
        fontFamily = Comfortaa_semiBold,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)