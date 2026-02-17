package com.example.studentcrudapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.studentcrudapp.viewmodel.StudentViewModel

/**
 * SortDialog - Sort Options Selection Dialog
 *
 * Displays a dialog with sort options for the student list.
 * Shows checkmark next to currently selected option.
 *
 * @param currentSort Currently selected sort option
 * @param onDismiss Callback when dialog is dismissed
 * @param onSortSelected Callback when sort option is selected
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-06
 */
@Composable
fun SortDialog(
    currentSort: StudentViewModel.SortOption,
    onDismiss: () -> Unit,
    onSortSelected: (StudentViewModel.SortOption) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Sort By",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                SortOption(
                    label = "Name (A-Z)",
                    selected = currentSort == StudentViewModel.SortOption.NAME_ASC,
                    onClick = {
                        onSortSelected(StudentViewModel.SortOption.NAME_ASC)
                        onDismiss()
                    }
                )

                SortOption(
                    label = "Name (Z-A)",
                    selected = currentSort == StudentViewModel.SortOption.NAME_DESC,
                    onClick = {
                        onSortSelected(StudentViewModel.SortOption.NAME_DESC)
                        onDismiss()
                    }
                )

                SortOption(
                    label = "Newest First",
                    selected = currentSort == StudentViewModel.SortOption.NEWEST,
                    onClick = {
                        onSortSelected(StudentViewModel.SortOption.NEWEST)
                        onDismiss()
                    }
                )

                SortOption(
                    label = "Oldest First",
                    selected = currentSort == StudentViewModel.SortOption.OLDEST,
                    onClick = {
                        onSortSelected(StudentViewModel.SortOption.OLDEST)
                        onDismiss()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

@Composable
private fun SortOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
