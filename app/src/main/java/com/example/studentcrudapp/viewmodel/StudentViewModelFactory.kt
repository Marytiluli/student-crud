package com.example.studentcrudapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studentcrudapp.data.repository.StudentRepository

/**
 * StudentViewModelFactory - Factory for creating StudentViewModel
 *
 * Custom ViewModelProvider.Factory that enables dependency injection
 * for ViewModels. Required because ViewModels need constructor parameters
 * (repository) that can't be provided by the default factory.
 *
 * Usage in Composable:
 * ```
 * val context = LocalContext.current
 * val database = AppDatabase.getDatabase(context)
 * val repository = StudentRepository(database.studentDao())
 * val viewModel: StudentViewModel = viewModel(
 *     factory = StudentViewModelFactory(repository)
 * )
 * ```
 *
 * Benefits:
 * - Decouples ViewModel creation from UI
 * - Enables testability (can inject mock repository)
 * - Follows SOLID principles (Dependency Injection)
 * - Type-safe ViewModel creation
 *
 * Alternative Approaches (Future):
 * - Hilt/Dagger for automatic DI
 * - Koin for lightweight DI
 * - Manual DI container
 *
 * @property repository StudentRepository instance to inject
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-31
 */
class StudentViewModelFactory(
    private val repository: StudentRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given ViewModel class.
     *
     * This method is called by the ViewModelProvider when requesting a ViewModel.
     * It checks if the requested class is StudentViewModel and creates it with
     * the injected repository.
     *
     * @param modelClass Class of the ViewModel to create
     * @return New ViewModel instance
     * @throws IllegalArgumentException if unknown ViewModel class
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
