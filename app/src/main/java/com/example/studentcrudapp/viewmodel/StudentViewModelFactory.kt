package com.example.studentcrudapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentcrudapp.data.repository.StudentRepository

/**
 * StudentViewModelFactory - Factory for Creating StudentViewModel
 *
 * Implements ViewModelProvider.Factory to create ViewModel instances
 * with constructor parameters (dependency injection).
 *
 * Why Factory Pattern?
 * - ViewModels cannot have constructor parameters by default
 * - Factory allows passing dependencies (repository)
 * - Type-safe ViewModel creation
 * - Survives configuration changes
 *
 * Usage:
 * ```
 * val viewModel: StudentViewModel = viewModel(
 *     factory = StudentViewModelFactory(repository)
 * )
 * ```
 *
 * @property repository StudentRepository instance to inject
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-05
 */
class StudentViewModelFactory(
    private val repository: StudentRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given ViewModel class.
     *
     * @param modelClass Class of ViewModel to create
     * @return New ViewModel instance with injected dependencies
     * @throws IllegalArgumentException if ViewModel class is unknown
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
