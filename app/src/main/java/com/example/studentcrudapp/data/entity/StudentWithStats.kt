package com.example.studentcrudapp.data.entity

import androidx.room.Embedded

/**
 * StudentWithStats - Data Class with Calculated Statistics
 *
 * Combines student entity with calculated statistics for detailed views.
 * Used in student detail screen to display comprehensive information.
 *
 * This class wraps a Student entity and adds calculated fields for:
 * - Total number of courses enrolled
 * - Average grade/GPA
 * - Attendance percentage
 * - Days since enrollment
 * - Last activity timestamp
 *
 * Room Integration:
 * - Uses @Embedded for flat structure in queries
 * - Can be used with @Relation for join queries (future)
 * - Supports complex query projections
 *
 * Use Cases:
 * - Student detail screen
 * - Analytics and reports
 * - Leaderboard rankings
 * - Performance tracking
 *
 * Usage:
 * ```
 * data class StudentWithStats(
 *     @Embedded val student: Student,
 *     val totalCourses: Int = 0,
 *     val averageGrade: Float = 0f,
 *     val attendanceRate: Float = 0f
 * )
 * ```
 *
 * @property student The core student entity
 * @property totalCourses Number of courses enrolled (calculated)
 * @property averageGrade Average grade across all courses (calculated)
 * @property attendanceRate Attendance percentage (calculated)
 * @property daysSinceEnrollment Days since student enrollment (calculated)
 * @property lastActivityDate Last recorded activity timestamp
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-04
 */
data class StudentWithStats(
    @Embedded
    val student: Student,
    val totalCourses: Int = 0,
    val averageGrade: Float = 0f,
    val attendanceRate: Float = 0f,
    val daysSinceEnrollment: Int = 0,
    val lastActivityDate: Long? = null
) {
    /**
     * Calculates performance status based on grades and attendance.
     *
     * Categories:
     * - Excellent: GPA >= 3.5 AND attendance >= 90%
     * - Good: GPA >= 3.0 AND attendance >= 80%
     * - Satisfactory: GPA >= 2.5 AND attendance >= 70%
     * - Needs Improvement: Below satisfactory thresholds
     *
     * @return Performance status as string
     */
    fun getPerformanceStatus(): String {
        return when {
            averageGrade >= 3.5f && attendanceRate >= 90f -> "Excellent"
            averageGrade >= 3.0f && attendanceRate >= 80f -> "Good"
            averageGrade >= 2.5f && attendanceRate >= 70f -> "Satisfactory"
            else -> "Needs Improvement"
        }
    }

    /**
     * Checks if student is at risk academically.
     * At-risk criteria:
     * - GPA below 2.0, OR
     * - Attendance below 70%, OR
     * - No activity in last 30 days
     *
     * @return true if student is at risk
     */
    fun isAtRisk(): Boolean {
        val thirtyDaysAgo = System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000L)
        val lastActivity = lastActivityDate ?: student.createdAt

        return averageGrade < 2.0f ||
                attendanceRate < 70f ||
                lastActivity < thirtyDaysAgo
    }

    /**
     * Checks if student is eligible for honors.
     * Honors criteria:
     * - GPA >= 3.5, AND
     * - Attendance >= 95%, AND
     * - Enrolled in at least 4 courses
     *
     * @return true if eligible for honors
     */
    fun isHonorStudent(): Boolean {
        return averageGrade >= 3.5f &&
                attendanceRate >= 95f &&
                totalCourses >= 4
    }

    /**
     * Gets a color indicator based on performance.
     * Used for UI visual feedback.
     *
     * @return Color string ("green", "yellow", "red")
     */
    fun getPerformanceColor(): String {
        return when {
            isHonorStudent() -> "green"
            isAtRisk() -> "red"
            else -> "yellow"
        }
    }

    /**
     * Formats days since enrollment as human-readable string.
     * Examples: "15 days", "2 months", "1 year"
     *
     * @return Formatted duration string
     */
    fun getEnrollmentDuration(): String {
        return when {
            daysSinceEnrollment < 30 -> "$daysSinceEnrollment days"
            daysSinceEnrollment < 365 -> "${daysSinceEnrollment / 30} months"
            else -> "${daysSinceEnrollment / 365} year${if (daysSinceEnrollment / 365 > 1) "s" else ""}"
        }
    }
}
