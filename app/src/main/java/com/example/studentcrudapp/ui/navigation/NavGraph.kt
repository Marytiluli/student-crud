package com.example.studentcrudapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentcrudapp.ui.screens.StudentListScreen
import com.example.studentcrudapp.utils.Constants
import com.example.studentcrudapp.viewmodel.StudentViewModel

/**
 * NavGraph - Navigation Configuration for the Application
 *
 * Defines all navigation routes and screen destinations using
 * Navigation Compose. Handles screen transitions and back stack
 * management automatically.
 *
 * Navigation Architecture:
 * - Centralized navigation logic
 * - Type-safe route definitions
 * - Composable-based destinations
 * - Automatic back stack handling
 *
 * Current Routes:
 * - student_list: Main screen (start destination)
 * - settings: Settings screen (Week 2)
 * - student_detail/{id}: Student detail screen (Week 2)
 * - course_list: Course management (Week 2)
 * - analytics: Analytics dashboard (Week 3)
 * - backup: Backup & restore (Week 2)
 *
 * Benefits:
 * - Single source of truth for navigation
 * - Easy to add new screens
 * - Handles deep linking (future)
 * - Supports navigation arguments
 *
 * Usage:
 * ```
 * val navController = rememberNavController()
 * NavGraph(
 *     navController = navController,
 *     viewModel = viewModel
 * )
 * ```
 *
 * @param navController Navigation controller for screen transitions
 * @param viewModel StudentViewModel shared across screens
 * @param modifier Optional modifier for customization
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-02
 */
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: StudentViewModel,
    modifier: Modifier = Modifier
) {
    /**
     * NavHost is the container for all navigation destinations.
     * It manages the back stack and screen transitions.
     *
     * Parameters:
     * - navController: Controls navigation actions
     * - startDestination: Initial screen to show
     * - modifier: Layout modifiers
     *
     * Each composable() defines a route and its content.
     */
    NavHost(
        navController = navController,
        startDestination = Constants.ROUTE_STUDENT_LIST,
        modifier = modifier
    ) {
        // ===========================
        // STUDENT LIST SCREEN (Main)
        // ===========================

        /**
         * Main screen showing all students.
         * Start destination of the app.
         *
         * Route: "student_list"
         * Features:
         * - Student list with search
         * - Add/Edit/Delete operations
         * - Navigation to other screens
         */
        composable(route = Constants.ROUTE_STUDENT_LIST) {
            StudentListScreen(
                viewModel = viewModel,
                onNavigateToSettings = {
                    // Navigate to settings screen
                    navController.navigate(Constants.ROUTE_SETTINGS)
                }
            )
        }

        // ===========================
        // SETTINGS SCREEN (Week 2)
        // ===========================

        /**
         * Settings and preferences screen.
         * Will be implemented in Week 2.
         *
         * Route: "settings"
         * Features:
         * - Theme selection
         * - Language selection
         * - Data management
         */
        composable(route = Constants.ROUTE_SETTINGS) {
            // TODO: Implement SettingsScreen in Week 2
            // SettingsScreen(
            //     onNavigateBack = { navController.popBackStack() }
            // )
        }

        // ===========================
        // STUDENT DETAIL SCREEN (Week 2)
        // ===========================

        /**
         * Student detail screen with statistics.
         * Will be implemented in Week 2.
         *
         * Route: "student_detail/{studentId}"
         * Arguments: studentId (Int)
         * Features:
         * - Detailed student information
         * - Grades, attendance, timeline
         * - Edit/delete actions
         */
        // TODO: Implement in Week 2
        // composable(
        //     route = Constants.ROUTE_STUDENT_DETAIL,
        //     arguments = listOf(
        //         navArgument(Constants.ARG_STUDENT_ID) {
        //             type = NavType.IntType
        //         }
        //     )
        // ) { backStackEntry ->
        //     val studentId = backStackEntry.arguments?.getInt(Constants.ARG_STUDENT_ID)
        //     StudentDetailScreen(
        //         studentId = studentId ?: 0,
        //         viewModel = viewModel,
        //         onNavigateBack = { navController.popBackStack() }
        //     )
        // }

        // ===========================
        // COURSE LIST SCREEN (Week 2)
        // ===========================

        /**
         * Course management screen.
         * Will be implemented in Week 2.
         *
         * Route: "course_list"
         * Features:
         * - List of all courses
         * - Add/Edit/Delete courses
         * - Filter by category
         */
        // TODO: Implement in Week 2

        // ===========================
        // ANALYTICS SCREEN (Week 3)
        // ===========================

        /**
         * Analytics dashboard with charts.
         * Will be implemented in Week 3.
         *
         * Route: "analytics"
         * Features:
         * - Student statistics
         * - Charts and graphs
         * - Performance metrics
         */
        // TODO: Implement in Week 3

        // ===========================
        // BACKUP SCREEN (Week 2)
        // ===========================

        /**
         * Backup and restore screen.
         * Will be implemented in Week 2.
         *
         * Route: "backup"
         * Features:
         * - Create backups
         * - Restore from backup
         * - Export to CSV/JSON
         */
        // TODO: Implement in Week 2
    }
}

/**
 * Navigation Helper Object
 *
 * Provides type-safe navigation functions.
 * Prevents errors from hardcoded route strings.
 *
 * Usage:
 * ```
 * Navigation.navigateToStudentDetail(navController, studentId = 123)
 * ```
 */
object Navigation {
    /**
     * Navigates to student detail screen.
     *
     * @param navController Navigation controller
     * @param studentId ID of student to display
     */
    fun navigateToStudentDetail(navController: NavHostController, studentId: Int) {
        navController.navigate("student_detail/$studentId")
    }

    /**
     * Navigates to settings screen.
     *
     * @param navController Navigation controller
     */
    fun navigateToSettings(navController: NavHostController) {
        navController.navigate(Constants.ROUTE_SETTINGS)
    }

    /**
     * Navigates to course list screen.
     *
     * @param navController Navigation controller
     */
    fun navigateToCourseList(navController: NavHostController) {
        navController.navigate(Constants.ROUTE_COURSE_LIST)
    }

    /**
     * Navigates to analytics screen.
     *
     * @param navController Navigation controller
     */
    fun navigateToAnalytics(navController: NavHostController) {
        navController.navigate(Constants.ROUTE_ANALYTICS)
    }

    /**
     * Navigates to backup screen.
     *
     * @param navController Navigation controller
     */
    fun navigateToBackup(navController: NavHostController) {
        navController.navigate(Constants.ROUTE_BACKUP)
    }

    /**
     * Navigates back to previous screen.
     *
     * @param navController Navigation controller
     * @return true if navigation successful, false if at start destination
     */
    fun navigateBack(navController: NavHostController): Boolean {
        return navController.popBackStack()
    }
}
