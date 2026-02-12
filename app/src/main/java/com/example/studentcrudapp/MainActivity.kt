package com.example.studentcrudapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentcrudapp.data.database.AppDatabase
import com.example.studentcrudapp.data.repository.StudentRepository
import com.example.studentcrudapp.ui.screens.StudentListScreen
import com.example.studentcrudapp.ui.theme.StudentCrudAppTheme
import com.example.studentcrudapp.viewmodel.StudentViewModel
import com.example.studentcrudapp.viewmodel.StudentViewModelFactory

/**
 * MainActivity - Entry Point of the Student CRUD Application
 *
 * This activity serves as the host for the entire Jetpack Compose UI.
 * It sets up the application theme and provides the container for
 * all screens and navigation.
 *
 * Responsibilities:
 * - Initialize database connection
 * - Create repository and ViewModel instances
 * - Set up dependency injection (manual DI pattern)
 * - Apply Material Design 3 theme
 * - Enable edge-to-edge display
 * - Host navigation graph (future)
 *
 * Architecture:
 * - Single activity with Compose
 * - MVVM pattern with Repository
 * - Manual dependency injection
 * - Lifecycle-aware components
 *
 * Features:
 * - Edge-to-edge display for modern UI
 * - Dynamic color support (Material You)
 * - Dark theme support
 * - Proper lifecycle handling
 *
 * Dependency Flow:
 * MainActivity → Database → Repository → ViewModel → UI
 *
 * Future Enhancements:
 * - Hilt/Dagger for automatic DI
 * - Navigation component for multi-screen navigation
 * - WorkManager for background tasks
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-29
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created.
     *
     * Initializes the Compose UI with the app theme and sets up
     * the surface container for all composable content.
     *
     * Steps:
     * 1. Enable edge-to-edge display
     * 2. Initialize database singleton
     * 3. Create repository instance
     * 4. Create ViewModel with factory
     * 5. Set up Compose UI with theme
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display (draws behind system bars)
        enableEdgeToEdge()

        // Set up Compose UI
        setContent {
            // Apply Material Design 3 theme with dynamic colors
            StudentCrudAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Main content - Initialize and display student list
                    StudentManagerApp()
                }
            }
        }
    }

    /**
     * Clean up resources when activity is destroyed.
     * Note: Room handles database cleanup automatically,
     * but this is here for future manual cleanup if needed.
     */
    override fun onDestroy() {
        super.onDestroy()
        // Database will be closed automatically when app process ends
        // Manual cleanup not needed for Room with singleton pattern
    }
}

/**
 * StudentManagerApp - Root Composable Function
 *
 * Sets up the dependency injection and initializes the main UI.
 * This composable handles:
 * - Database initialization (singleton pattern)
 * - Repository creation with DAO injection
 * - ViewModel creation with factory pattern
 * - Main screen composition
 *
 * Architecture Pattern:
 * - Manual Dependency Injection
 * - Factory pattern for ViewModel creation
 * - Repository pattern for data abstraction
 *
 * Why Manual DI?
 * - Simple and clear dependency flow
 * - No external DI framework needed (yet)
 * - Easy to understand for learning
 * - Can migrate to Hilt/Koin later
 *
 * Composable Lifecycle:
 * - Created once per composition
 * - Database singleton ensures single instance
 * - ViewModel survives configuration changes
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-02
 */
@Composable
fun StudentManagerApp() {
    // ===========================
    // DEPENDENCY INJECTION SETUP
    // ===========================

    // Get application context (stable across configuration changes)
    val context = LocalContext.current

    // Initialize database singleton
    // This will return existing instance if already created
    val database = AppDatabase.getDatabase(context)

    // Get DAO from database
    val studentDao = database.studentDao()

    // Create repository with DAO injection
    val repository = StudentRepository(studentDao)

    // Create ViewModel with factory pattern
    // ViewModelProvider ensures ViewModel survives configuration changes
    val viewModel: StudentViewModel = viewModel(
        factory = StudentViewModelFactory(repository)
    )

    // ===========================
    // UI COMPOSITION
    // ===========================

    // Display main student list screen
    StudentListScreen(
        viewModel = viewModel,
        onNavigateToSettings = {
            // TODO: Implement navigation to settings screen
            // Will be added in Week 2 when navigation graph is set up
        }
    )
}
