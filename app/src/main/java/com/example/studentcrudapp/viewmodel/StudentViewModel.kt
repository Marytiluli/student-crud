package com.example.studentcrudapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentcrudapp.data.entity.Student
import com.example.studentcrudapp.data.repository.StudentRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * StudentViewModel - Main ViewModel for Student List Screen
 *
 * Manages UI state and business logic for the student list screen.
 * Follows MVVM architecture pattern with reactive state management.
 *
 * Features:
 * - Reactive student list with automatic UI updates
 * - Search functionality with live filtering
 * - Sort options (A-Z, Z-A, newest, oldest)
 * - CRUD operations (create, read, update, delete)
 * - Loading and error states
 * - Empty state detection
 *
 * State Management:
 * - Uses StateFlow for UI state (cached, conflated)
 * - SharedFlow for one-time events (messages, navigation)
 * - Combines multiple flows for derived state
 *
 * Lifecycle:
 * - Scoped to composition lifecycle
 * - Automatically cancels coroutines when cleared
 * - Survives configuration changes
 *
 * @property repository StudentRepository for data operations
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-31
 */
class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    // ===========================
    // STATE MANAGEMENT
    // ===========================

    /**
     * Current search query entered by user.
     * Empty string shows all students.
     */
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    /**
     * Current sort option selected by user.
     */
    private val _sortOption = MutableStateFlow(SortOption.NAME_ASC)
    val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

    /**
     * Loading state for async operations.
     */
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    /**
     * Error message for failed operations.
     * Null if no error.
     */
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    /**
     * One-time events for UI (messages, navigation).
     * Use SharedFlow for events that shouldn't be re-triggered on config change.
     */
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    // ===========================
    // DERIVED STATE
    // ===========================

    /**
     * Student list based on current search and sort options.
     * Automatically updates when repository data changes.
     */
    val students: StateFlow<List<Student>> = combine(
        _searchQuery,
        _sortOption
    ) { query, sort ->
        Pair(query, sort)
    }.flatMapLatest { (query, sort) ->
        if (query.isBlank()) {
            // No search - return sorted list
            when (sort) {
                SortOption.NAME_ASC -> repository.getAllStudents()
                SortOption.NAME_DESC -> repository.getAllStudentsDescending()
                SortOption.NEWEST -> repository.getStudentsByNewest()
                SortOption.OLDEST -> repository.getStudentsByOldest()
            }
        } else {
            // Search active - filter and sort
            repository.searchStudents(query).map { list ->
                when (sort) {
                    SortOption.NAME_ASC -> list.sortedBy { it.name }
                    SortOption.NAME_DESC -> list.sortedByDescending { it.name }
                    SortOption.NEWEST -> list.sortedByDescending { it.createdAt }
                    SortOption.OLDEST -> list.sortedBy { it.createdAt }
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    /**
     * Total student count for statistics.
     */
    val studentCount: StateFlow<Int> = repository.getStudentCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    /**
     * Empty state detection.
     * True when no students match current filters.
     */
    val isEmpty: StateFlow<Boolean> = students
        .map { it.isEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )

    // ===========================
    // USER ACTIONS
    // ===========================

    /**
     * Updates the search query.
     * Triggers automatic list filtering.
     *
     * @param query New search term
     */
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query.trim()
    }

    /**
     * Clears the current search query.
     * Shows all students again.
     */
    fun clearSearch() {
        _searchQuery.value = ""
    }

    /**
     * Changes the sort option.
     * Triggers automatic list reordering.
     *
     * @param option New sort option
     */
    fun onSortOptionChange(option: SortOption) {
        _sortOption.value = option
    }

    /**
     * Inserts a new student into the database.
     * Shows success/error message via events.
     *
     * @param student Student to insert
     */
    fun insertStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.insertStudent(student)

            _isLoading.value = false

            if (result > 0) {
                _uiEvent.emit(UiEvent.ShowMessage("Student added successfully"))
            } else {
                _errorMessage.value = "Failed to add student"
                _uiEvent.emit(UiEvent.ShowError("Failed to add student"))
            }
        }
    }

    /**
     * Updates an existing student.
     *
     * @param student Student with updated fields
     */
    fun updateStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.updateStudent(student)

            _isLoading.value = false

            if (result > 0) {
                _uiEvent.emit(UiEvent.ShowMessage("Student updated successfully"))
            } else {
                _errorMessage.value = "Failed to update student"
                _uiEvent.emit(UiEvent.ShowError("Failed to update student"))
            }
        }
    }

    /**
     * Deletes a student from the database.
     * Shows undo option in snackbar.
     *
     * @param student Student to delete
     */
    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.deleteStudent(student)

            _isLoading.value = false

            if (result > 0) {
                _uiEvent.emit(UiEvent.StudentDeleted(student))
            } else {
                _errorMessage.value = "Failed to delete student"
                _uiEvent.emit(UiEvent.ShowError("Failed to delete student"))
            }
        }
    }

    /**
     * Restores a previously deleted student (undo).
     *
     * @param student Student to restore
     */
    fun restoreStudent(student: Student) {
        insertStudent(student)
    }

    /**
     * Clears the current error message.
     */
    fun clearError() {
        _errorMessage.value = null
    }

    // ===========================
    // HELPER CLASSES
    // ===========================

    /**
     * Sort options for student list.
     */
    enum class SortOption {
        NAME_ASC,    // A to Z
        NAME_DESC,   // Z to A
        NEWEST,      // Recently added first
        OLDEST       // Oldest first
    }

    /**
     * One-time UI events.
     * These don't persist across configuration changes.
     */
    sealed class UiEvent {
        data class ShowMessage(val message: String) : UiEvent()
        data class ShowError(val message: String) : UiEvent()
        data class StudentDeleted(val student: Student) : UiEvent()
    }
}
