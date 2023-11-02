package ru.cpt.fsp.ui.views

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 34.dp,
    height: Dp = 18.dp,
    checkedThumbColor: Color = MaterialTheme.colors.onPrimary,
    uncheckedThumbColor: Color = MaterialTheme.colors.onPrimary,
    checkedTrackColor: Color = MaterialTheme.colors.primary,
    uncheckedTrackColor: Color = MaterialTheme.colors.onSurface.copy(0.1f),
    checkedThumbDisabledColor: Color = MaterialTheme.colors.onPrimary,
    uncheckedThumbDisabledColor: Color = MaterialTheme.colors.onPrimary,
    checkedTrackDisabledColor: Color = MaterialTheme.colors.primary.copy(0.1f),
    uncheckedTrackDisabledColor: Color = MaterialTheme.colors.onSurface.copy(0.04f),
    gapBetweenThumbAndTrackEdge: Dp = 2.dp,
    cornerSize: Dp = 20.dp,
    iconInnerPadding: Dp = 0.dp,
    thumbSize: Dp = 14.dp,
    padding: Dp = 3.dp,
    enabled: Boolean = true,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val alignment by animateAlignmentAsState(if (checked) 1f else -1f)
    Box(
        modifier = modifier
            .padding(horizontal = padding)
            .background(
                color = if (checked) {
                    if (enabled) checkedTrackColor else checkedTrackDisabledColor
                } else {
                    if (enabled) uncheckedTrackColor else uncheckedTrackDisabledColor
                },
                shape = RoundedCornerShape(cornerSize)
            )
            .size(width = width, height = height)
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = interactionSource,
                onClick = remember(checked, onCheckedChange) { { onCheckedChange(!checked) } }
            ),
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {
            Canvas(
                Modifier
                    .size(size = thumbSize)
                    .fillMaxSize()
                    .padding(all = iconInnerPadding)
            ) {
                drawTrack(
                    if (checked) {
                        if (enabled) checkedThumbColor else checkedThumbDisabledColor
                    } else {
                        if (enabled) uncheckedThumbColor else uncheckedThumbDisabledColor
                    },
                    thumbSize.toPx(),
                    thumbSize.toPx()
                )
            }
        }
    }
}


private fun DrawScope.drawTrack(trackColor: Color, trackWidth: Float, strokeWidth: Float) {
    val strokeRadius = strokeWidth / 2
    drawLine(
        trackColor,
        Offset(strokeRadius, center.y),
        Offset(trackWidth - strokeRadius, center.y),
        strokeWidth,
        StrokeCap.Round
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float,
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue, label = "")
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}

@Preview
@Composable
private fun SwitcherPrev() {
    MaterialTheme {
        Switch(checked = false, onCheckedChange = { _ -> }, modifier = Modifier)
    }
}