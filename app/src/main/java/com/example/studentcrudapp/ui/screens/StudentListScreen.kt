package com.example.studentcrudapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentcrudapp.data.entity.Student
import com.example.studentcrudapp.ui.components.*
import com.example.studentcrudapp.viewmodel.StudentViewModel

/**
 * StudentListScreen - Main Screen of the Application
 *
 * Displays the list of all students with the following features:
 * - Searchable student list
 * - Real-time filtering
 * - Sort options (A-Z, Z-A, newest, oldest)
 * - Add new student (FAB)
 * - Edit existing student
 * - Delete student with confirmation
 * - Empty state when no students
 * - Loading indicators
 * - Error handling with snackbar
 *
 * Screen Structure:
 * ┌─────────────────────────────────────┐
 * │ TopAppBar (Title + Settings)        │
 * ├─────────────────────────────────────┤
 * │ SearchBar                           │
 * │ FilterChips (All, IT, Business...)  │
 * ├─────────────────────────────────────┤
 * │ LazyColumn (Student Cards)          │
 * │  • Student Card 1                   │
 * │  • Student Card 2                   │
 * │  • Student Card 3                   │
 * │  ...                                │
 * │                           FAB (+)   │
 * └─────────────────────────────────────┘
 *
 * State Management:
 * - Uses ViewModel for data operations
 * - Collects StateFlow for reactive UI
 * - Handles one-time events (messages, navigation)
 * - Manages dialog visibility states
 *
 * Architecture:
 * - MVVM pattern
 * - Unidirectional data flow
 * - Composable UI components
 *
 * @param viewModel StudentViewModel for data operations
 * @param onNavigateToSettings Callback to navigate to settings screen
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-02
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    viewModel: StudentViewModel,
    onNavigateToSettings: () -> Unit
) {
    // ===========================
    // STATE COLLECTION
    // ===========================

    // Collect students from ViewModel
    val students by viewModel.students.collectAsState()

    // Collect search query
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Collect loading state
    val isLoading by viewModel.isLoading.collectAsState()

    // Collect empty state
    val isEmpty by viewModel.isEmpty.collectAsState()

    // ===========================
    // LOCAL STATE
    // ===========================

    // Dialog visibility states
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    // Selected student for edit/delete
    var selectedStudent by remember { mutableStateOf<Student?>(null) }

    // Snackbar host state for messages
    val snackbarHostState = remember { SnackbarHostState() }

    // ===========================
    // EVENT HANDLING
    // ===========================

    // Collect one-time events from ViewModel
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is StudentViewModel.UiEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is StudentViewModel.UiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Long
                    )
                }
                is StudentViewModel.UiEvent.StudentDeleted -> {
                    val result = snackbarHostState.showSnackbar(
                        message = "Student deleted",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Long
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.restoreStudent(event.student)
                    }
                }
            }
        }
    }

    // ===========================
    // SCAFFOLD LAYOUT
    // ===========================

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Student Manager",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add student"
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // ===========================
            // SEARCH BAR
            // ===========================

            SearchBar(
                query = searchQuery,
                onQueryChange = { viewModel.onSearchQueryChange(it) },
                placeholder = "Search students...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // ===========================
            // STUDENT LIST
            // ===========================

            when {
                // Loading state
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                // Empty state
                isEmpty -> {
                    EmptyState(
                        title = if (searchQuery.isEmpty()) {
                            "No Students Yet"
                        } else {
                            "No Results Found"
                        },
                        description = if (searchQuery.isEmpty()) {
                            "Add your first student to get started"
                        } else {
                            "Try adjusting your search"
                        },
                        actionText = if (searchQuery.isEmpty()) "Add Student" else null,
                        onActionClick = if (searchQuery.isEmpty()) {
                            { showAddDialog = true }
                        } else null
                    )
                }

                // Show student list
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = students,
                            key = { student -> student.id }
                        ) { student ->
                            StudentCard(
                                student = student,
                                onEditClick = {
                                    selectedStudent = student
                                    showEditDialog = true
                                },
                                onDeleteClick = {
                                    selectedStudent = student
                                    showDeleteConfirmation = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // ===========================
    // DIALOGS
    // ===========================

    // Add student dialog
    if (showAddDialog) {
        StudentDialog(
            onDismiss = { showAddDialog = false },
            onSave = { student ->
                viewModel.insertStudent(student)
                showAddDialog = false
            }
        )
    }

    // Edit student dialog
    if (showEditDialog && selectedStudent != null) {
        StudentDialog(
            student = selectedStudent,
            onDismiss = {
                showEditDialog = false
                selectedStudent = null
            },
            onSave = { student ->
                viewModel.updateStudent(student)
                showEditDialog = false
                selectedStudent = null
            }
        )
    }

    // Delete confirmation dialog
    if (showDeleteConfirmation && selectedStudent != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteConfirmation = false
                selectedStudent = null
            },
            title = { Text("Delete Student?") },
            text = {
                Text("Are you sure you want to delete ${selectedStudent?.name}? This action cannot be undone.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedStudent?.let { viewModel.deleteStudent(it) }
                        showDeleteConfirmation = false
                        selectedStudent = null
                    }
                ) {
                    Text(
                        "Delete",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                        selectedStudent = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
