package com.example.studentcrudapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * EmptyState Component - Placeholder for Empty Lists
 *
 * Displays a friendly message when no data is available.
 * Follows Material Design empty state guidelines with:
 * - Large icon illustration
 * - Primary message (headline)
 * - Secondary descriptive text
 * - Optional action button
 * - Centered vertically and horizontally
 *
 * Use Cases:
 * - No students in database
 * - No search results found
 * - No items in filtered list
 * - No notifications available
 *
 * Design:
 * - Icon: 120dp size, low emphasis color
 * - Title: Headline small style
 * - Description: Body medium style
 * - Button: Optional filled tonal button
 *
 * Usage:
 * ```
 * if (students.isEmpty()) {
 *     EmptyState(
 *         icon = Icons.Default.School,
 *         title = "No Students Yet",
 *         description = "Add your first student to get started",
 *         actionText = "Add Student",
 *         onActionClick = { showAddDialog = true }
 *     )
 * }
 * ```
 *
 * @param icon Icon to display (Material icon)
 * @param title Main message headline
 * @param description Supporting text explaining the state
 * @param actionText Optional button text
 * @param onActionClick Optional button click handler
 * @param modifier Optional modifier for customization
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-01
 */
@Composable
fun EmptyState(
    icon: androidx.compose.ui.graphics.vector.ImageVector = Icons.Default.School,
    title: String,
    description: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    /**
     * Column layout centered in parent container.
     * Uses vertical arrangement for proper spacing.
     */
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Large icon illustration
        Icon(
            imageVector = icon,
            contentDescription = null, // Decorative only
            modifier = Modifier.size(120.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Main title
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description text
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Optional action button
        if (actionText != null && onActionClick != null) {
            Spacer(modifier = Modifier.height(24.dp))

            FilledTonalButton(
                onClick = onActionClick,
                contentPadding = PaddingValues(
                    horizontal = 24.dp,
                    vertical = 12.dp
                )
            ) {
                Text(text = actionText)
            }
        }
    }
}
