package com.example.studentcrudapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

/**
 * SearchBar Component - Material Design 3 Search TextField
 *
 * A reusable search bar component with the following features:
 * - Real-time search query updates
 * - Clear button when text is present
 * - Search icon indicator
 * - Keyboard dismiss on search action
 * - Outlined style with rounded corners
 * - Accessibility support
 *
 * Design:
 * - Full-width container with Material3 styling
 * - 56dp height (standard touch target)
 * - 28dp corner radius for pill shape
 * - Elevated surface appearance
 *
 * Usage:
 * ```
 * var searchQuery by remember { mutableStateOf("") }
 * SearchBar(
 *     query = searchQuery,
 *     onQueryChange = { searchQuery = it },
 *     placeholder = "Search students..."
 * )
 * ```
 *
 * @param query Current search text value
 * @param onQueryChange Callback when search text changes
 * @param placeholder Hint text shown when empty
 * @param modifier Optional modifier for customization
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-01
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = "Search...",
    modifier: Modifier = Modifier
) {
    // Keyboard controller for dismissing keyboard
    val keyboardController = LocalSoftwareKeyboardController.current

    // Focus requester for programmatic focus control
    val focusRequester = remember { FocusRequester() }

    /**
     * Outlined text field with search styling.
     * Uses Material3 OutlinedTextField for consistent appearance.
     */
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .focusRequester(focusRequester),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        leadingIcon = {
            // Search icon on the left
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            // Clear button appears when text is present
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onQueryChange("")
                        keyboardController?.hide()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                // Dismiss keyboard when user presses search
                keyboardController?.hide()
            }
        ),
        shape = MaterialTheme.shapes.extraLarge, // 28dp rounded corners
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}
