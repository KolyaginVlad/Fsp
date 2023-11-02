package ru.cpt.fsp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.cpt.fsp.R

private val montserratFamily = FontFamily(
    listOf(
        Font(R.font.montserrat_regular, weight = FontWeight.Normal),
        Font(R.font.montserrat_medium, weight = FontWeight.Medium),
        Font(R.font.montserrat_semibold, weight = FontWeight.SemiBold),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = montserratFamily,
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
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

val Typography.semiBold23: TextStyle
    get() = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 34.sp,
        fontSize = 23.sp,
    )

val Typography.medium23: TextStyle
    get() = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 34.sp,
        fontSize = 23.sp,
    )

val Typography.semiBold13: TextStyle
    get() = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 19.sp,
        fontSize = 13.sp,
    )

val Typography.medium16: TextStyle
    get() = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        fontSize = 16.sp,
    )

val Typography.medium13: TextStyle
    get() = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 19.sp,
        fontSize = 13.sp,
    )