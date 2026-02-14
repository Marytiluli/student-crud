package com.example.studentcrudapp

import com.example.studentcrudapp.data.entity.Student
import com.example.studentcrudapp.data.repository.StudentRepository
import com.example.studentcrudapp.viewmodel.StudentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * StudentViewModelTest - Unit Tests for StudentViewModel
 *
 * Tests the business logic and state management of StudentViewModel.
 * Uses JUnit 4, Mockito for mocking, and coroutine test utilities.
 *
 * Test Coverage:
 * - State initialization
 * - Search functionality
 * - Sort options
 * - CRUD operations
 * - Error handling
 * - Loading states
 *
 * Architecture:
 * - InstantTaskExecutorRule for LiveData testing
 * - TestDispatcher for coroutine testing
 * - Mockito for repository mocking
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-04
 */
@ExperimentalCoroutinesApi
class StudentViewModelTest {

    /**
     * Rule to make LiveData execute synchronously.
     * Required for testing ViewModels with LiveData.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Test dispatcher for controlling coroutine execution.
     */
    private val testDispatcher = StandardTestDispatcher()

    /**
     * Mock repository for testing without real database.
     */
    @Mock
    private lateinit var mockRepository: StudentRepository

    /**
     * ViewModel instance under test.
     */
    private lateinit var viewModel: StudentViewModel

    /**
     * Sample students for testing.
     */
    private val testStudents = listOf(
        Student(id = 1, name = "Alice Johnson", course = "Computer Science"),
        Student(id = 2, name = "Bob Smith", course = "Business Administration"),
        Student(id = 3, name = "Charlie Brown", course = "Fine Arts")
    )

    /**
     * Setup method run before each test.
     * Initializes mocks and test environment.
     */
    @Before
    fun setup() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this)

        // Set main dispatcher to test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Setup default repository behavior
        `when`(mockRepository.getAllStudents()).thenReturn(flowOf(testStudents))
        `when`(mockRepository.getStudentCount()).thenReturn(flowOf(testStudents.size))

        // Create ViewModel with mock repository
        viewModel = StudentViewModel(mockRepository)
    }

    /**
     * Cleanup method run after each test.
     * Resets main dispatcher.
     */
    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    // ===========================
    // INITIALIZATION TESTS
    // ===========================

    /**
     * Test: ViewModel initializes with default state
     */
    @Test
    fun `viewModel initializes with empty search query`() = runTest {
        // Given: ViewModel is created

        // When: Get initial search query
        val query = viewModel.searchQuery.first()

        // Then: Query should be empty
        assertEquals("", query)
    }

    /**
     * Test: ViewModel initializes with NAME_ASC sort option
     */
    @Test
    fun `viewModel initializes with NAME_ASC sort option`() = runTest {
        // Given: ViewModel is created

        // When: Get initial sort option
        val sortOption = viewModel.sortOption.first()

        // Then: Should be NAME_ASC
        assertEquals(StudentViewModel.SortOption.NAME_ASC, sortOption)
    }

    // ===========================
    // SEARCH TESTS
    // ===========================

    /**
     * Test: Search query updates correctly
     */
    @Test
    fun `onSearchQueryChange updates search query`() = runTest {
        // Given: Initial empty query

        // When: Update search query
        viewModel.onSearchQueryChange("Alice")
        advanceUntilIdle()

        // Then: Query should be updated
        val query = viewModel.searchQuery.first()
        assertEquals("Alice", query)
    }

    /**
     * Test: Clear search resets query to empty
     */
    @Test
    fun `clearSearch resets query to empty string`() = runTest {
        // Given: Search query with text
        viewModel.onSearchQueryChange("Bob")
        advanceUntilIdle()

        // When: Clear search
        viewModel.clearSearch()
        advanceUntilIdle()

        // Then: Query should be empty
        val query = viewModel.searchQuery.first()
        assertEquals("", query)
    }

    // ===========================
    // SORT TESTS
    // ===========================

    /**
     * Test: Sort option changes correctly
     */
    @Test
    fun `onSortOptionChange updates sort option`() = runTest {
        // Given: Default NAME_ASC sort

        // When: Change to NEWEST
        viewModel.onSortOptionChange(StudentViewModel.SortOption.NEWEST)
        advanceUntilIdle()

        // Then: Sort option should be NEWEST
        val sortOption = viewModel.sortOption.first()
        assertEquals(StudentViewModel.SortOption.NEWEST, sortOption)
    }

    // ===========================
    // INSERT TESTS
    // ===========================

    /**
     * Test: Insert student succeeds
     */
    @Test
    fun `insertStudent calls repository insert`() = runTest {
        // Given: New student to insert
        val newStudent = Student(name = "David Wilson", course = "Engineering")
        `when`(mockRepository.insertStudent(newStudent)).thenReturn(4L)

        // When: Insert student
        viewModel.insertStudent(newStudent)
        advanceUntilIdle()

        // Then: Repository insert should be called
        verify(mockRepository).insertStudent(newStudent)
    }

    // ===========================
    // UPDATE TESTS
    // ===========================

    /**
     * Test: Update student succeeds
     */
    @Test
    fun `updateStudent calls repository update`() = runTest {
        // Given: Existing student to update
        val updatedStudent = testStudents[0].copy(name = "Alice Cooper")
        `when`(mockRepository.updateStudent(updatedStudent)).thenReturn(1)

        // When: Update student
        viewModel.updateStudent(updatedStudent)
        advanceUntilIdle()

        // Then: Repository update should be called
        verify(mockRepository).updateStudent(updatedStudent)
    }

    // ===========================
    // DELETE TESTS
    // ===========================

    /**
     * Test: Delete student succeeds
     */
    @Test
    fun `deleteStudent calls repository delete`() = runTest {
        // Given: Student to delete
        val studentToDelete = testStudents[0]
        `when`(mockRepository.deleteStudent(studentToDelete)).thenReturn(1)

        // When: Delete student
        viewModel.deleteStudent(studentToDelete)
        advanceUntilIdle()

        // Then: Repository delete should be called
        verify(mockRepository).deleteStudent(studentToDelete)
    }

    /**
     * Test: Restore student after delete
     */
    @Test
    fun `restoreStudent re-inserts deleted student`() = runTest {
        // Given: Deleted student
        val deletedStudent = testStudents[0]
        `when`(mockRepository.insertStudent(deletedStudent)).thenReturn(1L)

        // When: Restore student
        viewModel.restoreStudent(deletedStudent)
        advanceUntilIdle()

        // Then: Repository insert should be called
        verify(mockRepository).insertStudent(deletedStudent)
    }

    // ===========================
    // ERROR HANDLING TESTS
    // ===========================

    /**
     * Test: Error message is cleared
     */
    @Test
    fun `clearError resets error message to null`() = runTest {
        // Given: ViewModel with no error

        // When: Clear error
        viewModel.clearError()
        advanceUntilIdle()

        // Then: Error message should be null
        val errorMessage = viewModel.errorMessage.first()
        assertEquals(null, errorMessage)
    }

    // ===========================
    // EMPTY STATE TESTS
    // ===========================

    /**
     * Test: Empty state when no students
     */
    @Test
    fun `isEmpty is true when student list is empty`() = runTest {
        // Given: Repository returns empty list
        `when`(mockRepository.getAllStudents()).thenReturn(flowOf(emptyList()))
        val emptyViewModel = StudentViewModel(mockRepository)

        // When: Collect students
        advanceUntilIdle()

        // Then: isEmpty should be true
        val isEmpty = emptyViewModel.isEmpty.first()
        assertTrue(isEmpty)
    }
}