package com.example.studentcrudapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Student Entity - Room Database Table
 *
 * Represents a student record in the local database.
 * This entity defines the core structure for student data persistence.
 *
 * Table Structure:
 * - Auto-generated primary key (id)
 * - Student name with NOT NULL constraint
 * - Course/department information
 * - Optional profile photo URI
 * - Timestamps for creation and last update
 *
 * Indexes:
 * - name: Speeds up search and sorting by name
 * - course: Enables fast filtering by course
 *
 * Database Relationships:
 * - One-to-many with grades
 * - One-to-many with attendance
 * - Many-to-many with courses (via junction table)
 * - Many-to-many with tags (via junction table)
 *
 * @property id Unique identifier (auto-generated)
 * @property name Full name of the student (required, 2-50 chars)
 * @property course Course or department name (required)
 * @property photoUri Optional URI to profile photo in external storage
 * @property createdAt Timestamp when record was created (milliseconds)
 * @property updatedAt Timestamp when record was last modified (milliseconds)
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-30
 */
@Entity(
    tableName = "students",
    indices = [
        Index(value = ["name"]),
        Index(value = ["course"])
    ]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "course")
    val course: String,

    @ColumnInfo(name = "photo_uri")
    val photoUri: String? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
) {
    /**
     * Generates initials from student name for avatar display.
     *
     * Examples:
     * - "Mary Tiluli" → "MT"
     * - "John" → "J"
     * - "Sarah Jane Smith" → "SJ" (first two words only)
     *
     * @return String of uppercase initials (max 2 characters)
     */
    fun getInitials(): String {
        val parts = name.trim().split("\\s+".toRegex())
        return when {
            parts.isEmpty() -> ""
            parts.size == 1 -> parts[0].take(1).uppercase()
            else -> "${parts[0].take(1)}${parts[1].take(1)}".uppercase()
        }
    }

    /**
     * Checks if this student record has a profile photo.
     *
     * @return true if photoUri is not null and not empty
     */
    fun hasPhoto(): Boolean = !photoUri.isNullOrEmpty()

    /**
     * Creates a copy of this student with updated timestamp.
     * Useful for update operations that need to track modification time.
     *
     * @return New Student instance with current timestamp
     */
    fun withUpdatedTimestamp(): Student = copy(updatedAt = System.currentTimeMillis())
}
