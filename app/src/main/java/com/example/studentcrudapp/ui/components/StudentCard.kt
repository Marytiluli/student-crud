package com.example.studentcrudapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.studentcrudapp.data.entity.Student

/**
 * StudentCard Component - List Item for Student Display
 *
 * A Material Design 3 card that displays student information with:
 * - Circular avatar with initials or photo
 * - Student name (primary text)
 * - Course name (secondary text)
 * - Edit and delete action buttons
 * - Elevation and ripple effects
 * - Swipe-to-delete support (future)
 *
 * Layout Structure:
 * ┌─────────────────────────────────────────┐
 * │ ┌────┐                                  │
 * │ │ MT │  Mary Tiluli              ✏️ 🗑️ │
 * │ └────┘  Computer Science               │
 * └─────────────────────────────────────────┘
 *
 * Design Specifications:
 * - Card: 12dp corner radius, 2dp elevation
 * - Avatar: 48dp diameter, circular
 * - Padding: 16dp all sides
 * - Min height: 80dp
 * - Text: Single line with ellipsis
 *
 * Interactions:
 * - Card click: Navigate to detail screen
 * - Edit button: Open edit dialog
 * - Delete button: Show confirmation dialog
 *
 * Usage:
 * ```
 * StudentCard(
 *     student = student,
 *     onEditClick = { showEditDialog(student) },
 *     onDeleteClick = { showDeleteConfirmation(student) }
 * )
 * ```
 *
 * @param student Student entity to display
 * @param onEditClick Callback when edit button is clicked
 * @param onDeleteClick Callback when delete button is clicked
 * @param modifier Optional modifier for customization
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-01
 */
@Composable
fun StudentCard(
    student: Student,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * Elevated card with Material3 styling.
     * Provides automatic elevation and shape.
     */
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        /**
         * Row layout for horizontal arrangement.
         * Avatar on left, info in center, actions on right.
         */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // ===========================
            // AVATAR SECTION
            // ===========================

            /**
             * Circular avatar showing student initials.
             * Background color based on primary color.
             */
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.getInitials(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // ===========================
            // INFO SECTION
            // ===========================

            /**
             * Column for student name and course.
             * Takes remaining space between avatar and actions.
             */
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Student name - primary text
                Text(
                    text = student.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Course name - secondary text
                Text(
                    text = student.course,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // ===========================
            // ACTION BUTTONS SECTION
            // ===========================

            /**
             * Row of action buttons.
             * Edit and delete icons.
             */
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Edit button
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit student",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                // Delete button
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete student",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
