package com.example.studentcrudapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.studentcrudapp.data.entity.Student

/**
 * StudentDialog Component - Add/Edit Student Form Dialog
 *
 * A Material Design 3 dialog for creating new students or editing existing ones.
 * Includes:
 * - Input validation with real-time error messages
 * - Keyboard navigation (IME actions)
 * - Auto-capitalization for names
 * - Save/Cancel actions
 * - Loading state during save operation
 *
 * Validation Rules:
 * - Name: Required, 2-50 characters
 * - Course: Required, 1-30 characters
 * - Both fields must be non-empty after trim
 *
 * Dialog Structure:
 * ┌─────────────────────────────────────┐
 * │  Add/Edit Student                   │
 * │                                     │
 * │  ┌─────────────────────────────┐   │
 * │  │ Student Name *              │   │
 * │  └─────────────────────────────┘   │
 * │                                     │
 * │  ┌─────────────────────────────┐   │
 * │  │ Course/Department *         │   │
 * │  └─────────────────────────────┘   │
 * │                                     │
 * │         [Cancel]  [Save/Update]    │
 * └─────────────────────────────────────┘
 *
 * Usage:
 * ```
 * var showDialog by remember { mutableStateOf(false) }
 * if (showDialog) {
 *     StudentDialog(
 *         student = null, // null for add, student for edit
 *         onDismiss = { showDialog = false },
 *         onSave = { student ->
 *             viewModel.insertStudent(student)
 *             showDialog = false
 *         }
 *     )
 * }
 * ```
 *
 * @param student Existing student to edit (null for new student)
 * @param onDismiss Callback when dialog is dismissed
 * @param onSave Callback when save button is clicked with validated student
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-01
 */
@Composable
fun StudentDialog(
    student: Student? = null,
    onDismiss: () -> Unit,
    onSave: (Student) -> Unit
) {
    // ===========================
    // STATE MANAGEMENT
    // ===========================

    // Form field values
    var name by remember { mutableStateOf(student?.name ?: "") }
    var course by remember { mutableStateOf(student?.course ?: "") }

    // Validation error messages
    var nameError by remember { mutableStateOf<String?>(null) }
    var courseError by remember { mutableStateOf<String?>(null) }

    // Loading state for save operation
    var isLoading by remember { mutableStateOf(false) }

    // Focus manager for keyboard navigation
    val focusManager = LocalFocusManager.current

    // Determine if we're editing or adding
    val isEditing = student != null
    val dialogTitle = if (isEditing) "Edit Student" else "Add New Student"
    val saveButtonText = if (isEditing) "Update" else "Save"

    // ===========================
    // VALIDATION FUNCTIONS
    // ===========================

    /**
     * Validates the name field.
     * Returns true if valid, false otherwise.
     * Updates nameError state with appropriate message.
     */
    fun validateName(): Boolean {
        nameError = when {
            name.isBlank() -> "Name is required"
            name.trim().length < 2 -> "Name must be at least 2 characters"
            name.trim().length > 50 -> "Name must not exceed 50 characters"
            else -> null
        }
        return nameError == null
    }

    /**
     * Validates the course field.
     * Returns true if valid, false otherwise.
     * Updates courseError state with appropriate message.
     */
    fun validateCourse(): Boolean {
        courseError = when {
            course.isBlank() -> "Course is required"
            course.trim().length > 30 -> "Course must not exceed 30 characters"
            else -> null
        }
        return courseError == null
    }

    /**
     * Validates all fields and saves if valid.
     */
    fun handleSave() {
        val isNameValid = validateName()
        val isCourseValid = validateCourse()

        if (isNameValid && isCourseValid) {
            isLoading = true

            val studentToSave = if (isEditing) {
                // Update existing student
                student!!.copy(
                    name = name.trim(),
                    course = course.trim()
                )
            } else {
                // Create new student
                Student(
                    name = name.trim(),
                    course = course.trim()
                )
            }

            onSave(studentToSave)
            // Note: Calling code should handle dismissing dialog after save completes
        }
    }

    // ===========================
    // DIALOG UI
    // ===========================

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ===========================
                // DIALOG TITLE
                // ===========================

                Text(
                    text = dialogTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // ===========================
                // NAME INPUT FIELD
                // ===========================

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        // Clear error when user starts typing
                        if (nameError != null) nameError = null
                    },
                    label = { Text("Student Name *") },
                    placeholder = { Text("Enter student name") },
                    isError = nameError != null,
                    supportingText = {
                        if (nameError != null) {
                            Text(
                                text = nameError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            validateName()
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )

                // ===========================
                // COURSE INPUT FIELD
                // ===========================

                OutlinedTextField(
                    value = course,
                    onValueChange = {
                        course = it
                        // Clear error when user starts typing
                        if (courseError != null) courseError = null
                    },
                    label = { Text("Course/Department *") },
                    placeholder = { Text("Enter course name") },
                    isError = courseError != null,
                    supportingText = {
                        if (courseError != null) {
                            Text(
                                text = courseError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            validateCourse()
                            focusManager.clearFocus()
                            handleSave()
                        }
                    )
                )

                // ===========================
                // ACTION BUTTONS
                // ===========================

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Cancel button
                    TextButton(
                        onClick = onDismiss,
                        enabled = !isLoading
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Save/Update button
                    Button(
                        onClick = { handleSave() },
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Text(saveButtonText)
                        }
                    }
                }
            }
        }
    }
}
