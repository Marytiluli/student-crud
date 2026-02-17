package com.example.studentcrudapp.data.dao

import androidx.room.*
import com.example.studentcrudapp.data.entity.Course
import kotlinx.coroutines.flow.Flow

/**
 * CourseDao - Data Access Object for Course Entity
 *
 * Provides database operations for Course records.
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-06
 */
@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(course: Course): Long

    @Update
    suspend fun update(course: Course): Int

    @Delete
    suspend fun delete(course: Course): Int

    @Query("SELECT * FROM courses WHERE id = :courseId")
    fun getCourseById(courseId: Int): Flow<Course?>

    @Query("SELECT * FROM courses ORDER BY name ASC")
    fun getAllCourses(): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE department LIKE '%' || :department || '%' ORDER BY name ASC")
    fun getCoursesByDepartment(department: String): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE name LIKE '%' || :query || '%' OR code LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchCourses(query: String): Flow<List<Course>>

    @Query("SELECT COUNT(*) FROM courses")
    fun getCourseCount(): Flow<Int>

    @Query("DELETE FROM courses")
    suspend fun deleteAllCourses(): Int
}
