package com.example.studentcrudapp.utils

import com.example.studentcrudapp.utils.Constants.MAX_COURSE_LENGTH
import com.example.studentcrudapp.utils.Constants.MAX_NAME_LENGTH
import com.example.studentcrudapp.utils.Constants.MIN_NAME_LENGTH

/**
 * ValidationUtils - Input Validation Helper Object
 *
 * Provides reusable validation functions for all input fields
 * throughout the application. Ensures consistent validation rules
 * and error messages.
 *
 * Features:
 * - Student name validation
 * - Course name validation
 * - Email validation (future)
 * - Phone number validation (future)
 * - Grade validation (future)
 * - Centralized error messages
 *
 * Benefits:
 * - Single source of truth for validation logic
 * - Consistent error messages
 * - Easy to update rules
 * - Testable validation functions
 * - Type-safe sealed result classes
 *
 * Architecture:
 * - Object (singleton pattern)
 * - Pure functions (no side effects)
 * - Returns ValidationResult sealed class
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-03
 */
object ValidationUtils {

    /**
     * ValidationResult - Result of a validation operation
     *
     * Sealed class representing validation outcome.
     * Either Success or Error with message.
     */
    sealed class ValidationResult {
        object Success : ValidationResult()
        data class Error(val message: String) : ValidationResult()
    }

    // ===========================
    // STUDENT NAME VALIDATION
    // ===========================

    /**
     * Validates student name field.
     *
     * Rules:
     * - Cannot be empty or blank
     * - Must be at least 2 characters (after trim)
     * - Cannot exceed 50 characters
     * - Should contain only letters and spaces
     *
     * @param name Student name to validate
     * @return ValidationResult.Success or ValidationResult.Error
     */
    fun validateStudentName(name: String): ValidationResult {
        val trimmedName = name.trim()

        return when {
            trimmedName.isBlank() -> {
                ValidationResult.Error("Name is required")
            }
            trimmedName.length < MIN_NAME_LENGTH -> {
                ValidationResult.Error("Name must be at least $MIN_NAME_LENGTH characters")
            }
            trimmedName.length > MAX_NAME_LENGTH -> {
                ValidationResult.Error("Name must not exceed $MAX_NAME_LENGTH characters")
            }
            !trimmedName.matches(Regex("^[a-zA-Z\\s]+$")) -> {
                ValidationResult.Error("Name should contain only letters and spaces")
            }
            else -> ValidationResult.Success
        }
    }

    /**
     * Quick check if student name is valid.
     *
     * @param name Student name to check
     * @return true if valid, false otherwise
     */
    fun isValidStudentName(name: String): Boolean {
        return validateStudentName(name) is ValidationResult.Success
    }

    // ===========================
    // COURSE NAME VALIDATION
    // ===========================

    /**
     * Validates course/department name field.
     *
     * Rules:
     * - Cannot be empty or blank
     * - Cannot exceed 30 characters
     * - Can contain letters, numbers, and spaces
     *
     * @param course Course name to validate
     * @return ValidationResult.Success or ValidationResult.Error
     */
    fun validateCourseName(course: String): ValidationResult {
        val trimmedCourse = course.trim()

        return when {
            trimmedCourse.isBlank() -> {
                ValidationResult.Error("Course is required")
            }
            trimmedCourse.length > MAX_COURSE_LENGTH -> {
                ValidationResult.Error("Course must not exceed $MAX_COURSE_LENGTH characters")
            }
            else -> ValidationResult.Success
        }
    }

    /**
     * Quick check if course name is valid.
     *
     * @param course Course name to check
     * @return true if valid, false otherwise
     */
    fun isValidCourseName(course: String): Boolean {
        return validateCourseName(course) is ValidationResult.Success
    }

    // ===========================
    // EMAIL VALIDATION (Future)
    // ===========================

    /**
     * Validates email address format.
     *
     * Rules:
     * - Must match standard email format
     * - Cannot be empty if required
     *
     * @param email Email address to validate
     * @param required Whether email is required field
     * @return ValidationResult.Success or ValidationResult.Error
     */
    fun validateEmail(email: String, required: Boolean = false): ValidationResult {
        val trimmedEmail = email.trim()

        return when {
            trimmedEmail.isEmpty() && !required -> {
                ValidationResult.Success
            }
            trimmedEmail.isEmpty() && required -> {
                ValidationResult.Error("Email is required")
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(trimmedEmail).matches() -> {
                ValidationResult.Error("Invalid email format")
            }
            else -> ValidationResult.Success
        }
    }

    // ===========================
    // PHONE NUMBER VALIDATION (Future)
    // ===========================

    /**
     * Validates phone number format.
     *
     * Rules:
     * - Should be 10-15 digits
     * - Can include country code
     * - Can include separators (+, -, spaces)
     *
     * @param phone Phone number to validate
     * @param required Whether phone is required field
     * @return ValidationResult.Success or ValidationResult.Error
     */
    fun validatePhoneNumber(phone: String, required: Boolean = false): ValidationResult {
        val cleanedPhone = phone.replace(Regex("[^0-9+]"), "")

        return when {
            cleanedPhone.isEmpty() && !required -> {
                ValidationResult.Success
            }
            cleanedPhone.isEmpty() && required -> {
                ValidationResult.Error("Phone number is required")
            }
            cleanedPhone.length < 10 || cleanedPhone.length > 15 -> {
                ValidationResult.Error("Phone number must be 10-15 digits")
            }
            else -> ValidationResult.Success
        }
    }

    // ===========================
    // GRADE VALIDATION (Future)
    // ===========================

    /**
     * Validates grade percentage.
     *
     * Rules:
     * - Must be a number
     * - Must be between 0 and 100
     *
     * @param grade Grade percentage as string
     * @return ValidationResult.Success or ValidationResult.Error
     */
    fun validateGrade(grade: String): ValidationResult {
        val gradeValue = grade.toIntOrNull()

        return when {
            gradeValue == null -> {
                ValidationResult.Error("Grade must be a number")
            }
            gradeValue < 0 || gradeValue > Constants.MAX_GRADE_PERCENTAGE -> {
                ValidationResult.Error("Grade must be between 0 and ${Constants.MAX_GRADE_PERCENTAGE}")
            }
            else -> ValidationResult.Success
        }
    }

    /**
     * Validates GPA value.
     *
     * Rules:
     * - Must be a number
     * - Must be between 0.0 and 4.0
     *
     * @param gpa GPA value as string
     * @return ValidationResult.Success or ValidationResult.Error
     */
    fun validateGPA(gpa: String): ValidationResult {
        val gpaValue = gpa.toFloatOrNull()

        return when {
            gpaValue == null -> {
                ValidationResult.Error("GPA must be a number")
            }
            gpaValue < Constants.MIN_GPA || gpaValue > Constants.MAX_GPA -> {
                ValidationResult.Error("GPA must be between ${Constants.MIN_GPA} and ${Constants.MAX_GPA}")
            }
            else -> ValidationResult.Success
        }
    }

    // ===========================
    // COMBINED VALIDATION
    // ===========================

    /**
     * Validates all student form fields at once.
     *
     * @param name Student name
     * @param course Course name
     * @return List of error messages (empty if all valid)
     */
    fun validateStudentForm(
        name: String,
        course: String
    ): List<String> {
        val errors = mutableListOf<String>()

        when (val nameResult = validateStudentName(name)) {
            is ValidationResult.Error -> errors.add(nameResult.message)
            else -> {}
        }

        when (val courseResult = validateCourseName(course)) {
            is ValidationResult.Error -> errors.add(courseResult.message)
            else -> {}
        }

        return errors
    }

    /**
     * Quick check if entire student form is valid.
     *
     * @param name Student name
     * @param course Course name
     * @return true if all fields valid, false otherwise
     */
    fun isValidStudentForm(name: String, course: String): Boolean {
        return validateStudentForm(name, course).isEmpty()
    }
}
