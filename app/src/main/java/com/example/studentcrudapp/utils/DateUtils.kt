package com.example.studentcrudapp.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * DateUtils - Date and Time Formatting Utilities
 *
 * Provides helper functions for date/time formatting and manipulation.
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-06
 */
object DateUtils {

    /**
     * Formats timestamp to display date.
     * Example: "Feb 6, 2026"
     */
    fun formatDate(timestamp: Long): String {
        val formatter = SimpleDateFormat(Constants.DATE_FORMAT_DISPLAY, Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    /**
     * Formats timestamp to full date and time.
     * Example: "Feb 6, 2026 10:30 AM"
     */
    fun formatDateTime(timestamp: Long): String {
        val formatter = SimpleDateFormat(Constants.TIMESTAMP_FORMAT, Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    /**
     * Formats timestamp for file names.
     * Example: "2026-02-06"
     */
    fun formatDateForFile(timestamp: Long): String {
        val formatter = SimpleDateFormat(Constants.DATE_FORMAT_FILE, Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    /**
     * Returns relative time string.
     * Example: "2 hours ago", "Yesterday", "3 days ago"
     */
    fun getRelativeTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            seconds < 60 -> "Just now"
            minutes < 60 -> "$minutes ${if (minutes == 1L) "minute" else "minutes"} ago"
            hours < 24 -> "$hours ${if (hours == 1L) "hour" else "hours"} ago"
            days == 1L -> "Yesterday"
            days < 7 -> "$days days ago"
            days < 30 -> "${days / 7} ${if (days / 7 == 1L) "week" else "weeks"} ago"
            days < 365 -> "${days / 30} ${if (days / 30 == 1L) "month" else "months"} ago"
            else -> "${days / 365} ${if (days / 365 == 1L) "year" else "years"} ago"
        }
    }

    /**
     * Calculates days between two timestamps.
     */
    fun daysBetween(start: Long, end: Long): Int {
        val diff = end - start
        return (diff / (1000 * 60 * 60 * 24)).toInt()
    }

    /**
     * Checks if timestamp is today.
     */
    fun isToday(timestamp: Long): Boolean {
        val cal1 = Calendar.getInstance().apply { timeInMillis = timestamp }
        val cal2 = Calendar.getInstance()
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }
}
