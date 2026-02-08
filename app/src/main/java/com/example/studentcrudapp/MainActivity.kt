package com.example.studentcrudapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.studentcrudapp.ui.theme.StudentCrudAppTheme

/**
 * MainActivity - Entry point of the Student CRUD Application
 *
 * This activity serves as the host for the entire Jetpack Compose UI.
 * It sets up the application theme and provides the container for
 * all screens and navigation.
 *
 * Features:
 * - Edge-to-edge display for modern UI
 * - Material Design 3 theming
 * - Single-activity architecture with Compose
 *
 * Architecture Pattern: MVVM
 * - ViewModels are initialized in Composables
 * - Repository pattern for data access
 * - Room database for persistence
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
                    // Main content will be added here in later commits
                    // Navigation graph will be initialized here
                }
            }
        }
    }
}
