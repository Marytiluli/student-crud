package com.example.studentcrudapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shape System for Material Design 3
 *
 * Defines corner radius values for different component sizes.
 * Material Design 3 uses rounded corners throughout for a softer,
 * more modern appearance.
 *
 * Shape Categories:
 * - Extra Small: 4dp (chips, tags)
 * - Small: 8dp (text fields, cards)
 * - Medium: 12dp (elevated cards)
 * - Large: 16dp (sheets, dialogs)
 * - Extra Large: 28dp (FABs, prominent surfaces)
 *
 * Usage:
 * ```
 * Card(
 *     shape = MaterialTheme.shapes.medium
 * ) { ... }
 * ```
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-31
 */
val Shapes = Shapes(
    /**
     * Extra Small Shape - 4dp corners.
     * Use for small components like chips and tags.
     */
    extraSmall = RoundedCornerShape(4.dp),

    /**
     * Small Shape - 8dp corners.
     * Use for text fields and small cards.
     */
    small = RoundedCornerShape(8.dp),

    /**
     * Medium Shape - 12dp corners.
     * Default for most cards and surfaces.
     */
    medium = RoundedCornerShape(12.dp),

    /**
     * Large Shape - 16dp corners.
     * Use for bottom sheets and navigation drawers.
     */
    large = RoundedCornerShape(16.dp),

    /**
     * Extra Large Shape - 28dp corners.
     * Use for FABs, dialogs, and prominent surfaces.
     */
    extraLarge = RoundedCornerShape(28.dp)
)
