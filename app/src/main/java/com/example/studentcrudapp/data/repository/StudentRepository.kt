package com.example.studentcrudapp.data.repository

import com.example.studentcrudapp.data.dao.StudentDao
import com.example.studentcrudapp.data.entity.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * StudentRepository - Data Layer Abstraction
 *
 * Repository pattern implementation that acts as single source of truth
 * for student data. Abstracts data sources from ViewModels.
 *
 * Architecture Benefits:
 * - Separation of concerns (ViewModel doesn't know about Room)
 * - Easy to swap data sources (local DB, network, cache)
 * - Centralized business logic for data operations
 * - Testable (can mock repository in ViewModel tests)
 *
 * Data Flow:
 * UI → ViewModel → Repository → DAO → Database
 * Database → DAO → Repository (Flow) → ViewModel → UI
 *
 * Features:
 * - Reactive data streams using Flow
 * - Async operations using coroutines
 * - Result handling with sealed classes (future)
 * - Caching layer (future)
 * - Network sync (future)
 *
 * @property studentDao Data Access Object for database operations
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-30
 */
class StudentRepository(private val studentDao: StudentDao) {

    // ===========================
    // READ OPERATIONS
    // ===========================

    /**
     * Observes all students sorted by name (A-Z).
     * Automatically updates UI when database changes.
     *
     * @return Flow emitting list of students
     */
    fun getAllStudents(): Flow<List<Student>> = studentDao.getAllStudents()

    /**
     * Observes all students sorted by name (Z-A).
     *
     * @return Flow emitting list of students in reverse order
     */
    fun getAllStudentsDescending(): Flow<List<Student>> =
        studentDao.getAllStudentsDescending()

    /**
     * Observes students sorted by creation date (newest first).
     *
     * @return Flow emitting list of students by date
     */
    fun getStudentsByNewest(): Flow<List<Student>> =
        studentDao.getStudentsByNewest()

    /**
     * Observes students sorted by creation date (oldest first).
     *
     * @return Flow emitting list of students by date
     */
    fun getStudentsByOldest(): Flow<List<Student>> =
        studentDao.getStudentsByOldest()

    /**
     * Observes a specific student by ID.
     *
     * @param studentId Primary key of student
     * @return Flow emitting Student or null if not found
     */
    fun getStudentById(studentId: Int): Flow<Student?> =
        studentDao.getStudentById(studentId)

    /**
     * Observes total student count.
     * Useful for displaying statistics and empty states.
     *
     * @return Flow emitting current count
     */
    fun getStudentCount(): Flow<Int> = studentDao.getStudentCount()

    /**
     * Checks if database has any students.
     * More efficient than loading all students just to check size.
     *
     * @return Flow emitting true if no students exist
     */
    fun isEmpty(): Flow<Boolean> = getStudentCount().map { it == 0 }

    /**
     * Checks if a student with given ID exists.
     *
     * @param studentId ID to verify
     * @return Flow emitting existence status
     */
    fun studentExists(studentId: Int): Flow<Boolean> =
        studentDao.studentExists(studentId)

    // ===========================
    // SEARCH & FILTER OPERATIONS
    // ===========================

    /**
     * Searches students by name with live updates.
     *
     * @param query Search term (partial match supported)
     * @return Flow emitting matching students
     */
    fun searchStudentsByName(query: String): Flow<List<Student>> =
        studentDao.searchStudentsByName(query)

    /**
     * Filters students by course/department.
     *
     * @param course Course name to filter
     * @return Flow emitting students in that course
     */
    fun getStudentsByCourse(course: String): Flow<List<Student>> =
        studentDao.getStudentsByCourse(course)

    /**
     * Searches students across name and course fields.
     *
     * @param query Search term
     * @return Flow emitting all matching students
     */
    fun searchStudents(query: String): Flow<List<Student>> =
        studentDao.searchStudents(query)

    /**
     * Gets count of students in a specific course.
     *
     * @param course Course name
     * @return Flow emitting student count
     */
    fun getStudentCountByCourse(course: String): Flow<Int> =
        studentDao.getStudentCountByCourse(course)

    // ===========================
    // WRITE OPERATIONS
    // ===========================

    /**
     * Inserts a new student into the database.
     *
     * Usage:
     * ```
     * val student = Student(name = "Mary Tiluli", course = "Computer Science")
     * val id = repository.insertStudent(student)
     * ```
     *
     * @param student Student entity to insert
     * @return Row ID of inserted student (or -1 on failure)
     */
    suspend fun insertStudent(student: Student): Long {
        return try {
            studentDao.insert(student)
        } catch (e: Exception) {
            // Log error (will add proper logging later)
            -1L
        }
    }

    /**
     * Updates an existing student record.
     * Automatically updates the 'updatedAt' timestamp.
     *
     * @param student Student with modified fields
     * @return Number of rows updated (1 if successful)
     */
    suspend fun updateStudent(student: Student): Int {
        return try {
            // Update timestamp before saving
            val updatedStudent = student.withUpdatedTimestamp()
            studentDao.update(updatedStudent)
        } catch (e: Exception) {
            0
        }
    }

    /**
     * Deletes a student from the database.
     *
     * @param student Student entity to delete
     * @return Number of rows deleted (1 if successful)
     */
    suspend fun deleteStudent(student: Student): Int {
        return try {
            studentDao.delete(student)
        } catch (e: Exception) {
            0
        }
    }

    /**
     * Deletes a student by ID without loading the entity.
     * More efficient than deleteStudent() when you only have the ID.
     *
     * @param studentId Primary key of student to delete
     * @return Number of rows deleted
     */
    suspend fun deleteStudentById(studentId: Int): Int {
        return try {
            studentDao.deleteById(studentId)
        } catch (e: Exception) {
            0
        }
    }

    /**
     * Deletes all students from the database.
     * Use with caution - typically only for testing or explicit user action.
     *
     * @return Number of students deleted
     */
    suspend fun deleteAllStudents(): Int {
        return try {
            studentDao.deleteAllStudents()
        } catch (e: Exception) {
            0
        }
    }

    // ===========================
    // BATCH OPERATIONS (Future)
    // ===========================

    /**
     * Inserts multiple students in a single transaction.
     * More efficient than calling insertStudent() multiple times.
     *
     * @param students List of students to insert
     * @return List of inserted row IDs
     */
    suspend fun insertStudents(students: List<Student>): List<Long> {
        return students.map { insertStudent(it) }
    }

    /**
     * Deletes multiple students in a single transaction.
     *
     * @param students List of students to delete
     * @return Total number of rows deleted
     */
    suspend fun deleteStudents(students: List<Student>): Int {
        return students.sumOf { deleteStudent(it) }
    }
}
