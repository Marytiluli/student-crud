package com.example.studentcrudapp.utils

/**
 * Constants - Application-Wide Constant Values
 *
 * Centralized location for all constant values used throughout the application.
 * Organized by category for easy maintenance and refactoring.
 *
 * Benefits:
 * - Single source of truth for magic numbers and strings
 * - Easy to update values across entire app
 * - Type-safe compile-time constants
 * - Improved code readability
 *
 * Categories:
 * - Database: Database configuration values
 * - Validation: Input validation limits
 * - UI: UI-related constants (animations, timeouts)
 * - Navigation: Navigation route names
 * - Storage: File paths and preferences keys
 * - Network: API endpoints (future)
 *
 * Usage:
 * ```
 * if (name.length < Constants.MIN_NAME_LENGTH) {
 *     // Show error
 * }
 * ```
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-01
 */
object Constants {

    // ===========================
    // DATABASE CONSTANTS
    // ===========================

    /**
     * Name of the Room database file.
     */
    const val DATABASE_NAME = "student_manager_db"

    /**
     * Current database version.
     * Increment when schema changes.
     */
    const val DATABASE_VERSION = 1

    /**
     * Database schema export directory.
     */
    const val SCHEMA_DIRECTORY = "schemas"

    // ===========================
    // VALIDATION CONSTANTS
    // ===========================

    /**
     * Minimum length for student name (2 characters).
     */
    const val MIN_NAME_LENGTH = 2

    /**
     * Maximum length for student name (50 characters).
     */
    const val MAX_NAME_LENGTH = 50

    /**
     * Maximum length for course name (30 characters).
     */
    const val MAX_COURSE_LENGTH = 30

    /**
     * Maximum length for search query (100 characters).
     */
    const val MAX_SEARCH_LENGTH = 100

    /**
     * Minimum GPA value (0.0).
     */
    const val MIN_GPA = 0.0f

    /**
     * Maximum GPA value (4.0).
     */
    const val MAX_GPA = 4.0f

    /**
     * Maximum grade percentage (100%).
     */
    const val MAX_GRADE_PERCENTAGE = 100

    // ===========================
    // UI CONSTANTS
    // ===========================

    /**
     * Standard animation duration in milliseconds (240ms).
     * Used for most transitions and animations.
     */
    const val ANIMATION_DURATION = 240

    /**
     * Fast animation duration (120ms).
     * Used for quick feedback animations.
     */
    const val ANIMATION_DURATION_FAST = 120

    /**
     * Slow animation duration (480ms).
     * Used for emphasis and dramatic transitions.
     */
    const val ANIMATION_DURATION_SLOW = 480

    /**
     * Snackbar display duration in milliseconds (3 seconds).
     */
    const val SNACKBAR_DURATION = 3000L

    /**
     * Debounce delay for search input (300ms).
     * Prevents excessive database queries while typing.
     */
    const val SEARCH_DEBOUNCE_DELAY = 300L

    /**
     * Timeout for stale data in milliseconds (5 seconds).
     * Used for Flow.stateIn() WhileSubscribed parameter.
     */
    const val STATE_FLOW_TIMEOUT = 5000L

    /**
     * Maximum number of search history items to keep.
     */
    const val MAX_SEARCH_HISTORY = 10

    // ===========================
    // NAVIGATION CONSTANTS
    // ===========================

    /**
     * Navigation route for student list screen.
     */
    const val ROUTE_STUDENT_LIST = "student_list"

    /**
     * Navigation route for student detail screen.
     * Requires student ID parameter.
     */
    const val ROUTE_STUDENT_DETAIL = "student_detail/{studentId}"

    /**
     * Navigation route for settings screen.
     */
    const val ROUTE_SETTINGS = "settings"

    /**
     * Navigation route for course list screen.
     */
    const val ROUTE_COURSE_LIST = "course_list"

    /**
     * Navigation route for analytics screen.
     */
    const val ROUTE_ANALYTICS = "analytics"

    /**
     * Navigation route for backup screen.
     */
    const val ROUTE_BACKUP = "backup"

    /**
     * Navigation argument key for student ID.
     */
    const val ARG_STUDENT_ID = "studentId"

    // ===========================
    // STORAGE CONSTANTS
    // ===========================

    /**
     * DataStore preferences file name.
     */
    const val PREFERENCES_NAME = "student_manager_preferences"

    /**
     * Preference key for theme mode.
     */
    const val PREF_THEME_MODE = "theme_mode"

    /**
     * Preference key for selected language.
     */
    const val PREF_LANGUAGE = "language"

    /**
     * Preference key for auto-backup setting.
     */
    const val PREF_AUTO_BACKUP = "auto_backup"

    /**
     * Preference key for last backup timestamp.
     */
    const val PREF_LAST_BACKUP = "last_backup"

    /**
     * Directory name for backup files.
     */
    const val BACKUP_DIRECTORY = "backups"

    /**
     * File extension for backup files.
     */
    const val BACKUP_FILE_EXTENSION = ".db"

    /**
     * File extension for CSV exports.
     */
    const val CSV_FILE_EXTENSION = ".csv"

    /**
     * File extension for JSON exports.
     */
    const val JSON_FILE_EXTENSION = ".json"

    // ===========================
    // DATE/TIME CONSTANTS
    // ===========================

    /**
     * Date format pattern for display (e.g., "Feb 1, 2026").
     */
    const val DATE_FORMAT_DISPLAY = "MMM d, yyyy"

    /**
     * Date format pattern for files (e.g., "2026-02-01").
     */
    const val DATE_FORMAT_FILE = "yyyy-MM-dd"

    /**
     * Timestamp format pattern (e.g., "Feb 1, 2026 10:30 AM").
     */
    const val TIMESTAMP_FORMAT = "MMM d, yyyy h:mm a"

    /**
     * Time format pattern (e.g., "10:30 AM").
     */
    const val TIME_FORMAT = "h:mm a"

    // ===========================
    // IMAGE CONSTANTS
    // ===========================

    /**
     * Maximum image file size in bytes (5 MB).
     */
    const val MAX_IMAGE_SIZE = 5 * 1024 * 1024

    /**
     * Image compression quality (0-100).
     */
    const val IMAGE_COMPRESSION_QUALITY = 80

    /**
     * Maximum image dimension in pixels.
     */
    const val MAX_IMAGE_DIMENSION = 1024

    // ===========================
    // PAGINATION CONSTANTS
    // ===========================

    /**
     * Default page size for paginated lists.
     */
    const val DEFAULT_PAGE_SIZE = 20

    /**
     * Initial page number.
     */
    const val INITIAL_PAGE = 1

    // ===========================
    // NOTIFICATION CONSTANTS
    // ===========================

    /**
     * Notification channel ID.
     */
    const val NOTIFICATION_CHANNEL_ID = "student_manager_channel"

    /**
     * Notification channel name.
     */
    const val NOTIFICATION_CHANNEL_NAME = "Student Manager"

    // ===========================
    // ERROR MESSAGES
    // ===========================

    /**
     * Generic error message.
     */
    const val ERROR_GENERIC = "An error occurred. Please try again."

    /**
     * Network error message.
     */
    const val ERROR_NETWORK = "No internet connection"

    /**
     * Database error message.
     */
    const val ERROR_DATABASE = "Database error occurred"

    // ===========================
    // SUCCESS MESSAGES
    // ===========================

    /**
     * Student added success message.
     */
    const val MSG_STUDENT_ADDED = "Student added successfully"

    /**
     * Student updated success message.
     */
    const val MSG_STUDENT_UPDATED = "Student updated successfully"

    /**
     * Student deleted success message.
     */
    const val MSG_STUDENT_DELETED = "Student deleted"

    /**
     * Backup created success message.
     */
    const val MSG_BACKUP_CREATED = "Backup created successfully"

    /**
     * Data exported success message.
     */
    const val MSG_DATA_EXPORTED = "Data exported successfully"
}
