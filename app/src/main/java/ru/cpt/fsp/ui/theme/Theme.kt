package ru.cpt.fsp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryVariant,
    secondary = Secondary,
    secondaryVariant = SecondaryVariant,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
)

@Composable
fun FspTheme(
    content: @Composable () -> Unit,
) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

val Colors.onPrimaryHighEmphasis: Color
    get() = onPrimary.copy(alpha = onPrimaryHighEmphasisAlfa)

val Colors.onPrimaryMediumEmphasis: Color
    get() = onPrimary.copy(alpha = onPrimaryMediumEmphasisAlfa)

val Colors.onPrimaryLowEmphasis: Color
    get() = onPrimary.copy(alpha = onPrimaryLowEmphasisAlfa)

val Colors.onSurfaceHighEmphasis: Color
    get() = onSurface.copy(alpha = onSurfaceHighEmphasisAlfa)

val Colors.onSurfaceMediumEmphasis: Color
    get() = onSurface.copy(alpha = onSurfaceMediumEmphasisAlfa)

val Colors.onSurfaceLowEmphasis: Color
    get() = onSurface.copy(alpha = onSurfaceLowEmphasisAlfa)