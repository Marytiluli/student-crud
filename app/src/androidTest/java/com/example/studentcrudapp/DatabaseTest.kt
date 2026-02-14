package com.example.studentcrudapp

import android.content.Context
import androidx.room.Room
import com.example.studentcrudapp.data.dao.StudentDao
import com.example.studentcrudapp.data.database.AppDatabase
import com.example.studentcrudapp.data.entity.Student
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * DatabaseTest - Instrumented Tests for Room Database
 *
 * Tests the Room database operations on an actual Android device/emulator.
 * Uses an in-memory database that is destroyed after tests complete.
 *
 * Test Coverage:
 * - CRUD operations (Create, Read, Update, Delete)
 * - Search functionality
 * - Filtering by course
 * - Count operations
 * - Flow emissions
 *
 * Architecture:
 * - AndroidJUnit4 test runner
 * - In-memory Room database
 * - Coroutine-based async tests
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-04
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    /**
     * In-memory database instance for testing.
     * Data is destroyed when process dies.
     */
    private lateinit var database: AppDatabase

    /**
     * DAO for student operations.
     */
    private lateinit var studentDao: StudentDao

    /**
     * Sample students for testing.
     */
    private val testStudent1 = Student(
        id = 1,
        name = "Alice Johnson",
        course = "Computer Science"
    )

    private val testStudent2 = Student(
        id = 2,
        name = "Bob Smith",
        course = "Business Administration"
    )

    private val testStudent3 = Student(
        id = 3,
        name = "Charlie Brown",
        course = "Computer Science"
    )

    /**
     * Setup method run before each test.
     * Creates an in-memory database.
     */
    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            .allowMainThreadQueries() // Only for testing
            .build()

        studentDao = database.studentDao()
    }

    /**
     * Cleanup method run after each test.
     * Closes the database.
     */
    @After
    fun closeDatabase() {
        database.close()
    }

    // ===========================
    // INSERT TESTS
    // ===========================

    /**
     * Test: Insert student and retrieve by ID
     */
    @Test
    fun insertStudentAndGetById() = runBlocking {
        // Given: Insert a student
        val id = studentDao.insert(testStudent1)

        // When: Retrieve by ID
        val retrieved = studentDao.getStudentById(id.toInt()).first()

        // Then: Should match inserted student
        assertNotNull(retrieved)
        assertEquals(testStudent1.name, retrieved?.name)
        assertEquals(testStudent1.course, retrieved?.course)
    }

    /**
     * Test: Insert multiple students
     */
    @Test
    fun insertMultipleStudents() = runBlocking {
        // Given: Insert multiple students
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)
        studentDao.insert(testStudent3)

        // When: Get all students
        val students = studentDao.getAllStudents().first()

        // Then: Should have 3 students
        assertEquals(3, students.size)
    }

    // ===========================
    // UPDATE TESTS
    // ===========================

    /**
     * Test: Update student name
     */
    @Test
    fun updateStudentName() = runBlocking {
        // Given: Insert a student
        val id = studentDao.insert(testStudent1)

        // When: Update student name
        val updated = testStudent1.copy(id = id.toInt(), name = "Alice Cooper")
        studentDao.update(updated)

        // Then: Retrieved student should have new name
        val retrieved = studentDao.getStudentById(id.toInt()).first()
        assertEquals("Alice Cooper", retrieved?.name)
    }

    // ===========================
    // DELETE TESTS
    // ===========================

    /**
     * Test: Delete student by entity
     */
    @Test
    fun deleteStudent() = runBlocking {
        // Given: Insert a student
        val id = studentDao.insert(testStudent1)
        val student = testStudent1.copy(id = id.toInt())

        // When: Delete student
        studentDao.delete(student)

        // Then: Student should not exist
        val retrieved = studentDao.getStudentById(id.toInt()).first()
        assertNull(retrieved)
    }

    /**
     * Test: Delete student by ID
     */
    @Test
    fun deleteStudentById() = runBlocking {
        // Given: Insert a student
        val id = studentDao.insert(testStudent1)

        // When: Delete by ID
        studentDao.deleteById(id.toInt())

        // Then: Student should not exist
        val retrieved = studentDao.getStudentById(id.toInt()).first()
        assertNull(retrieved)
    }

    /**
     * Test: Delete all students
     */
    @Test
    fun deleteAllStudents() = runBlocking {
        // Given: Insert multiple students
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)

        // When: Delete all
        studentDao.deleteAllStudents()

        // Then: No students should exist
        val students = studentDao.getAllStudents().first()
        assertTrue(students.isEmpty())
    }

    // ===========================
    // SEARCH TESTS
    // ===========================

    /**
     * Test: Search students by name
     */
    @Test
    fun searchStudentsByName() = runBlocking {
        // Given: Insert students
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)
        studentDao.insert(testStudent3)

        // When: Search for "Alice"
        val results = studentDao.searchStudentsByName("Alice").first()

        // Then: Should find Alice
        assertEquals(1, results.size)
        assertEquals("Alice Johnson", results[0].name)
    }

    /**
     * Test: Search returns empty for no matches
     */
    @Test
    fun searchReturnsEmptyForNoMatch() = runBlocking {
        // Given: Insert students
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)

        // When: Search for non-existent name
        val results = studentDao.searchStudentsByName("Zebra").first()

        // Then: Should return empty list
        assertTrue(results.isEmpty())
    }

    // ===========================
    // FILTER TESTS
    // ===========================

    /**
     * Test: Filter students by course
     */
    @Test
    fun filterStudentsByCourse() = runBlocking {
        // Given: Insert students with different courses
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)
        studentDao.insert(testStudent3)

        // When: Filter by Computer Science
        val results = studentDao.getStudentsByCourse("Computer Science").first()

        // Then: Should find 2 students
        assertEquals(2, results.size)
        assertTrue(results.all { it.course.contains("Computer Science") })
    }

    // ===========================
    // COUNT TESTS
    // ===========================

    /**
     * Test: Get student count
     */
    @Test
    fun getStudentCount() = runBlocking {
        // Given: Insert students
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)

        // When: Get count
        val count = studentDao.getStudentCount().first()

        // Then: Should be 2
        assertEquals(2, count)
    }

    /**
     * Test: Get student count by course
     */
    @Test
    fun getStudentCountByCourse() = runBlocking {
        // Given: Insert students
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)
        studentDao.insert(testStudent3)

        // When: Count Computer Science students
        val count = studentDao.getStudentCountByCourse("Computer Science").first()

        // Then: Should be 2
        assertEquals(2, count)
    }

    // ===========================
    // SORT TESTS
    // ===========================

    /**
     * Test: Students sorted by name ascending
     */
    @Test
    fun getStudentsSortedByNameAsc() = runBlocking {
        // Given: Insert students in random order
        studentDao.insert(testStudent3) // Charlie
        studentDao.insert(testStudent1) // Alice
        studentDao.insert(testStudent2) // Bob

        // When: Get all students (default sort)
        val students = studentDao.getAllStudents().first()

        // Then: Should be sorted A-Z
        assertEquals("Alice Johnson", students[0].name)
        assertEquals("Bob Smith", students[1].name)
        assertEquals("Charlie Brown", students[2].name)
    }

    /**
     * Test: Students sorted by name descending
     */
    @Test
    fun getStudentsSortedByNameDesc() = runBlocking {
        // Given: Insert students
        studentDao.insert(testStudent1)
        studentDao.insert(testStudent2)
        studentDao.insert(testStudent3)

        // When: Get students descending
        val students = studentDao.getAllStudentsDescending().first()

        // Then: Should be sorted Z-A
        assertEquals("Charlie Brown", students[0].name)
        assertEquals("Bob Smith", students[1].name)
        assertEquals("Alice Johnson", students[2].name)
    }
}