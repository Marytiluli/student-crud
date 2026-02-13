package com.example.studentcrudapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * LoadingIndicator Component - Progress Indicator with Optional Message
 *
 * A reusable loading indicator component that displays:
 * - Circular progress spinner
 * - Optional loading message below spinner
 * - Customizable size and colors
 * - Centered in parent container
 *
 * Design:
 * - Material Design 3 circular progress indicator
 * - Primary color for spinner
 * - Body medium typography for message
 * - Vertical layout with spacing
 *
 * Use Cases:
 * - Initial data loading
 * - Processing operations
 * - Saving/updating records
 * - Network requests (future)
 *
 * Variants:
 * - Small: 24dp (inline loading)
 * - Medium: 48dp (default, full screen)
 * - Large: 64dp (emphasis)
 *
 * Usage:
 * ```
 * if (isLoading) {
 *     LoadingIndicator(
 *         message = "Loading students...",
 *         size = 48.dp
 *     )
 * }
 * ```
 *
 * @param message Optional text message to display below spinner
 * @param size Size of the circular progress indicator
 * @param strokeWidth Width of the progress arc
 * @param modifier Optional modifier for customization
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-03
 */
@Composable
fun LoadingIndicator(
    message: String? = null,
    size: Dp = 48.dp,
    strokeWidth: Dp = 4.dp,
    modifier: Modifier = Modifier
) {
    /**
     * Column layout centered in container.
     * Spinner on top, message below (if provided).
     */
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /**
         * Circular progress indicator.
         * Indeterminate progress (continuous spinning).
         */
        CircularProgressIndicator(
            modifier = Modifier.size(size),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = strokeWidth
        )

        /**
         * Optional loading message.
         * Displayed below spinner with spacing.
         */
        if (message != null) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Inline Loading Indicator - Smaller variant for inline use
 *
 * Compact version for use within buttons, cards, or rows.
 *
 * Usage:
 * ```
 * Row {
 *     Text("Saving")
 *     InlineLoadingIndicator()
 * }
 * ```
 *
 * @param modifier Optional modifier for customization
 */
@Composable
fun InlineLoadingIndicator(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier.size(20.dp),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 2.dp
    )
}
