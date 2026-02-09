package com.example.studentcrudapp.data.dao

import androidx.room.*
import com.example.studentcrudapp.data.entity.Student
import kotlinx.coroutines.flow.Flow

/**
 * StudentDao - Data Access Object for Student Entity
 *
 * Provides database access methods for Student records using Room.
 * All operations return Flow for reactive UI updates except insert/update/delete.
 *
 * Architecture:
 * - Follows Repository pattern
 * - Uses Kotlin Flow for reactive data streams
 * - Supports coroutines for async operations
 *
 * Query Performance:
 * - Indexed columns (name, course) for fast queries
 * - LIKE operator with wildcards for flexible search
 * - ORDER BY clauses for sorted results
 *
 * Available Operations:
 * - CRUD: insert, update, delete, get by ID
 * - List: get all students with various sorting
 * - Search: filter by name or course
 * - Count: get total student count
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-30
 */
@Dao
interface StudentDao {

    /**
     * Inserts a new student into the database.
     *
     * @param student Student entity to insert
     * @return Row ID of the newly inserted student
     * @throws SQLiteConstraintException if constraints are violated
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(student: Student): Long

    /**
     * Updates an existing student record.
     * Updates the entire entity based on primary key.
     *
     * @param student Student entity with updated values
     * @return Number of rows updated (1 if successful, 0 if not found)
     */
    @Update
    suspend fun update(student: Student): Int

    /**
     * Deletes a student from the database.
     *
     * @param student Student entity to delete
     * @return Number of rows deleted (1 if successful, 0 if not found)
     */
    @Delete
    suspend fun delete(student: Student): Int

    /**
     * Deletes a student by ID.
     * More efficient than loading the entity first.
     *
     * @param studentId Primary key of student to delete
     * @return Number of rows deleted
     */
    @Query("DELETE FROM students WHERE id = :studentId")
    suspend fun deleteById(studentId: Int): Int

    /**
     * Retrieves a specific student by ID.
     * Returns null if not found.
     *
     * @param studentId Primary key of student
     * @return Flow emitting Student or null
     */
    @Query("SELECT * FROM students WHERE id = :studentId")
    fun getStudentById(studentId: Int): Flow<Student?>

    /**
     * Retrieves all students sorted by name (A-Z).
     * Emits new list whenever data changes.
     *
     * @return Flow emitting list of all students
     */
    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAllStudents(): Flow<List<Student>>

    /**
     * Retrieves all students sorted by name in descending order (Z-A).
     *
     * @return Flow emitting list of students sorted Z-A
     */
    @Query("SELECT * FROM students ORDER BY name DESC")
    fun getAllStudentsDescending(): Flow<List<Student>>

    /**
     * Retrieves students sorted by creation date (newest first).
     * Useful for showing recently added students.
     *
     * @return Flow emitting list sorted by creation date
     */
    @Query("SELECT * FROM students ORDER BY created_at DESC")
    fun getStudentsByNewest(): Flow<List<Student>>

    /**
     * Retrieves students sorted by creation date (oldest first).
     *
     * @return Flow emitting list sorted by creation date ascending
     */
    @Query("SELECT * FROM students ORDER BY created_at ASC")
    fun getStudentsByOldest(): Flow<List<Student>>

    /**
     * Searches students by name.
     * Uses LIKE operator with wildcards for partial matching.
     * Case-insensitive search.
     *
     * @param query Search string (e.g., "mary")
     * @return Flow emitting list of matching students
     */
    @Query("SELECT * FROM students WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchStudentsByName(query: String): Flow<List<Student>>

    /**
     * Filters students by course/department.
     * Exact match or partial match depending on query.
     *
     * @param course Course name to filter by
     * @return Flow emitting list of students in that course
     */
    @Query("SELECT * FROM students WHERE course LIKE '%' || :course || '%' ORDER BY name ASC")
    fun getStudentsByCourse(course: String): Flow<List<Student>>

    /**
     * Searches students by either name or course.
     * Combines multiple LIKE clauses with OR.
     *
     * @param query Search string to match against name or course
     * @return Flow emitting list of matching students
     */
    @Query("""
        SELECT * FROM students 
        WHERE name LIKE '%' || :query || '%' 
        OR course LIKE '%' || :query || '%'
        ORDER BY name ASC
    """)
    fun searchStudents(query: String): Flow<List<Student>>

    /**
     * Gets total count of students in database.
     * Useful for statistics and empty state checks.
     *
     * @return Flow emitting current student count
     */
    @Query("SELECT COUNT(*) FROM students")
    fun getStudentCount(): Flow<Int>

    /**
     * Gets count of students in a specific course.
     *
     * @param course Course name to count
     * @return Flow emitting count of students in that course
     */
    @Query("SELECT COUNT(*) FROM students WHERE course LIKE '%' || :course || '%'")
    fun getStudentCountByCourse(course: String): Flow<Int>

    /**
     * Deletes all students from the database.
     * Used for testing or complete data reset.
     * WARNING: This operation cannot be undone.
     *
     * @return Number of rows deleted
     */
    @Query("DELETE FROM students")
    suspend fun deleteAllStudents(): Int

    /**
     * Checks if a student with given ID exists.
     *
     * @param studentId ID to check
     * @return Flow emitting true if student exists
     */
    @Query("SELECT EXISTS(SELECT 1 FROM students WHERE id = :studentId)")
    fun studentExists(studentId: Int): Flow<Boolean>
}
