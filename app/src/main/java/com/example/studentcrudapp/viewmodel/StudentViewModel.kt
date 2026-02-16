package com.example.studentcrudapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentcrudapp.data.entity.Student
import com.example.studentcrudapp.data.repository.StudentRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * StudentViewModel - Complete Implementation
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _sortOption = MutableStateFlow(SortOption.NAME_ASC)
    val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    // ===========================
    // DERIVED STATE
    // ===========================

    val students: StateFlow<List<Student>> = combine(
        _searchQuery,
        _sortOption
    ) { query, sort ->
        Pair(query, sort)
    }.flatMapLatest { (query, sort) ->
        if (query.isBlank()) {
            when (sort) {
                SortOption.NAME_ASC -> repository.getAllStudents()
                SortOption.NAME_DESC -> repository.getAllStudentsDescending()
                SortOption.NEWEST -> repository.getStudentsByNewest()
                SortOption.OLDEST -> repository.getStudentsByOldest()
            }
        } else {
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

    val studentCount: StateFlow<Int> = repository.getStudentCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

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
     */
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    /**
     * Clears the search query.
     */
    fun clearSearch() {
        _searchQuery.value = ""
    }

    /**
     * Changes the sort option.
     */
    fun onSortOptionChange(option: SortOption) {
        _sortOption.value = option
    }

    /**
     * Inserts a new student.
     */
    fun insertStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val id = repository.insertStudent(student)
                if (id > 0) {
                    _uiEvent.emit(UiEvent.ShowMessage("Student added successfully"))
                } else {
                    _uiEvent.emit(UiEvent.ShowError("Failed to add student"))
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _uiEvent.emit(UiEvent.ShowError("Error: ${e.message}"))
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Updates an existing student.
     */
    fun updateStudent(student: Student) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val count = repository.updateStudent(student)
                if (count > 0) {
                    _uiEvent.emit(UiEvent.ShowMessage("Student updated successfully"))
                } else {
                    _uiEvent.emit(UiEvent.ShowError("Failed to update student"))
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _uiEvent.emit(UiEvent.ShowError("Error: ${e.message}"))
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Deletes a student with undo support.
     */
    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            try {
                val count = repository.deleteStudent(student)
                if (count > 0) {
                    _uiEvent.emit(UiEvent.StudentDeleted(student))
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _uiEvent.emit(UiEvent.ShowError("Error: ${e.message}"))
            }
        }
    }

    /**
     * Restores a deleted student (undo operation).
     */
    fun restoreStudent(student: Student) {
        viewModelScope.launch {
            try {
                repository.insertStudent(student)
                _uiEvent.emit(UiEvent.ShowMessage("Student restored"))
            } catch (e: Exception) {
                _uiEvent.emit(UiEvent.ShowError("Failed to restore student"))
            }
        }
    }

    /**
     * Clears error message.
     */
    fun clearError() {
        _errorMessage.value = null
    }

    // ===========================
    // ENUMS & SEALED CLASSES
    // ===========================

    /**
     * Sort options for student list.
     */
    enum class SortOption {
        NAME_ASC,
        NAME_DESC,
        NEWEST,
        OLDEST
    }

    /**
     * One-time UI events.
     */
    sealed class UiEvent {
        data class ShowMessage(val message: String) : UiEvent()
        data class ShowError(val message: String) : UiEvent()
        data class StudentDeleted(val student: Student) : UiEvent()
    }
}
