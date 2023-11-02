package ru.cpt.fsp.ui.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val RadioAnimationDuration = 100

private val RadioButtonRippleRadius = 24.dp
private val RadioButtonPadding = 3.dp
private val RadioButtonSize = 18.dp
private val RadioRadius = RadioButtonSize / 2
private val RadioButtonDotSize = 10.dp
private val RadioStrokeWidth = 1.dp


@Composable
fun Radio(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: CustomRadioButtonColors = CustomRadioButtonDefaults.colors(),
) {
    val dotRadius = animateDpAsState(
        targetValue = if (selected) RadioButtonDotSize / 2 else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration), label = ""
    )
    val radioColor = colors.radioColor(enabled, selected)
    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = RadioButtonRippleRadius
                )
            )
        } else {
            Modifier
        }
    Canvas(
        modifier
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .padding(RadioButtonPadding)
            .requiredSize(RadioButtonSize)
    ) {
        // Draw the radio button
        val strokeWidth = RadioStrokeWidth.toPx()
        drawCircle(
            radioColor.value,
            RadioRadius.toPx() - strokeWidth / 2,
            style = Stroke(strokeWidth)
        )
        if (dotRadius.value > 0.dp) {
            drawCircle(radioColor.value, dotRadius.value.toPx() - strokeWidth / 2, style = Fill)
        }
    }
}

@Stable
interface CustomRadioButtonColors {
    @Composable
    fun radioColor(enabled: Boolean, selected: Boolean): State<Color>
}

object CustomRadioButtonDefaults {
    @Composable
    fun colors(
        selectedColor: Color = MaterialTheme.colors.primary,
        selectedDisabledColor: Color = MaterialTheme.colors.primary.copy(0.1f),
        unselectedColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
        unselectedDisabledColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.38f),
    ): CustomRadioButtonColors {
        return remember(
            selectedColor,
            unselectedColor,
            selectedDisabledColor,
            unselectedDisabledColor
        ) {
            CustomDefaultRadioButtonColors(
                selectedColor = selectedColor,
                selectedDisabledColor = selectedDisabledColor,
                unselectedColor = unselectedColor,
                unselectedDisabledColor = unselectedDisabledColor
            )
        }
    }
}

@Immutable
private class CustomDefaultRadioButtonColors(
    private val selectedColor: Color,
    private val selectedDisabledColor: Color,
    private val unselectedColor: Color,
    private val unselectedDisabledColor: Color,
) : CustomRadioButtonColors {
    @Composable
    override fun radioColor(enabled: Boolean, selected: Boolean): State<Color> {
        val target = when {
            enabled && selected -> selectedColor
            enabled && !selected -> unselectedColor
            !enabled && selected -> selectedDisabledColor
            else -> unselectedDisabledColor
        }

        // If not enabled 'snap' to the disabled state, as there should be no animations between
        // enabled / disabled.
        return if (enabled) {
            animateColorAsState(target, tween(durationMillis = RadioAnimationDuration),
                label = ""
            )
        } else {
            rememberUpdatedState(target)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CustomDefaultRadioButtonColors

        if (selectedColor != other.selectedColor) return false
        if (unselectedColor != other.unselectedColor) return false
        if (selectedDisabledColor != other.selectedDisabledColor) return false
        if (unselectedDisabledColor != other.unselectedDisabledColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = selectedColor.hashCode()
        result = 31 * result + unselectedColor.hashCode()
        result = 31 * result + selectedDisabledColor.hashCode()
        result = 31 * result + unselectedDisabledColor.hashCode()
        return result
    }
}

@Preview
@Composable
private fun RadioPreview() {
    MaterialTheme {
        Radio(selected = true, onClick = {})
    }
}