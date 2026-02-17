package com.example.studentcrudapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Course Entity - Room Database Table
 *
 * Represents a course/subject in the system.
 * Links to students via enrollment relationships.
 *
 * Features:
 * - Course code (unique identifier)
 * - Course name and description
 * - Credits/hours
 * - Department/category
 * - Instructor information
 * - Timestamps
 *
 * Relationships:
 * - Many-to-many with Students (via StudentCourse junction)
 * - One-to-many with Grades
 * - One-to-many with Attendance
 *
 * @property id Unique identifier (auto-generated)
 * @property code Course code (e.g., "CS101")
 * @property name Course name (e.g., "Introduction to Programming")
 * @property description Course description
 * @property credits Number of credit hours
 * @property department Department/category
 * @property instructor Instructor name
 * @property createdAt Timestamp when created
 * @property updatedAt Timestamp when last updated
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-06
 */
@Entity(
    tableName = "courses",
    indices = [
        Index(value = ["code"], unique = true),
        Index(value = ["name"]),
        Index(value = ["department"])
    ]
)
data class Course(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "credits")
    val credits: Int = 3,

    @ColumnInfo(name = "department")
    val department: String,

    @ColumnInfo(name = "instructor")
    val instructor: String? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
) {
    /**
     * Generates a display name combining code and name.
     * Example: "CS101 - Introduction to Programming"
     */
    fun getDisplayName(): String = "$code - $name"

    /**
     * Checks if course has an instructor assigned.
     */
    fun hasInstructor(): Boolean = !instructor.isNullOrEmpty()

    /**
     * Returns initials for course code display.
     * Example: "CS101" → "CS"
     */
    fun getDepartmentCode(): String {
        return code.takeWhile { it.isLetter() }
    }
}
