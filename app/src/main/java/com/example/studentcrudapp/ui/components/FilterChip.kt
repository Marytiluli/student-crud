package com.example.studentcrudapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * FilterChip Component - Material3 Filter Chip
 *
 * A chip that allows filtering of content.
 * Shows selected state with checkmark icon.
 *
 * @param label Text to display
 * @param selected Whether chip is selected
 * @param onSelectedChange Callback when selection changes
 * @param modifier Optional modifier
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-06
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChip(
    label: String,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = { Text(label) },
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        } else null,
        modifier = modifier
    )
}
