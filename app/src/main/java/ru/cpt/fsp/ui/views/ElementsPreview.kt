package ru.cpt.fsp.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ElementsPreviewPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Switch(checked = false, onCheckedChange = { _ -> })
        Switch(checked = true, onCheckedChange = { _ -> })
        Switch(enabled = false, checked = true, onCheckedChange = { _ -> })
        Switch(enabled = false, checked = false, onCheckedChange = { _ -> })
        Radio(selected = true, onClick = { })
        Radio(selected = false, onClick = { })
        Radio(enabled = false, selected = true, onClick = { })
        Radio(enabled = false, selected = false, onClick = { })
        TriCheckbox(state = ToggleableState.On, onClick = { })
        TriCheckbox(state = ToggleableState.Indeterminate, onClick = { })
        TriCheckbox(state = ToggleableState.Off, onClick = { })
        TriCheckbox(state = ToggleableState.On, onClick = { }, enabled = false)
        TriCheckbox(state = ToggleableState.Indeterminate, onClick = { }, enabled = false)
        TriCheckbox(state = ToggleableState.Off, onClick = { }, enabled = false)
    }
}
