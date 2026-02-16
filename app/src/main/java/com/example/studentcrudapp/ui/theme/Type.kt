package com.example.studentcrudapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Typography - Material Design 3 Text Styles
 *
 * Defines the typography scale for the application.
 * Based on Material Design 3 type system.
 *
 * Type Scale:
 * - Display: Large, impactful text (titles, headlines)
 * - Headline: High-emphasis text (section headers)
 * - Title: Medium-emphasis text (list items, card titles)
 * - Body: Base text for reading (paragraphs, descriptions)
 * - Label: Small text (buttons, tabs, captions)
 *
 * Font Hierarchy:
 * - Display Large: 57sp
 * - Display Medium: 45sp
 * - Display Small: 36sp
 * - Headline Large: 32sp
 * - Headline Medium: 28sp
 * - Headline Small: 24sp
 * - Title Large: 22sp
 * - Title Medium: 16sp
 * - Title Small: 14sp
 * - Body Large: 16sp
 * - Body Medium: 14sp
 * - Body Small: 12sp
 * - Label Large: 14sp
 * - Label Medium: 12sp
 * - Label Small: 11sp
 *
 * Usage:
 * ```
 * Text(
 *     text = "Student Manager",
 *     style = MaterialTheme.typography.headlineLarge
 * )
 * ```
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-05
 */
val Typography = Typography(
    // ===========================
    // DISPLAY STYLES
    // ===========================

    /**
     * Display Large - 57sp
     * Use for: Hero text, main app title
     */
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),

    /**
     * Display Medium - 45sp
     * Use for: Large section headers
     */
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Display Small - 36sp
     * Use for: Prominent headers
     */
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    // ===========================
    // HEADLINE STYLES
    // ===========================

    /**
     * Headline Large - 32sp
     * Use for: Main screen titles
     */
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Headline Medium - 28sp
     * Use for: Section headers
     */
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Headline Small - 24sp
     * Use for: Subsection headers, dialog titles
     */
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // ===========================
    // TITLE STYLES
    // ===========================

    /**
     * Title Large - 22sp
     * Use for: Large emphasis text
     */
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Title Medium - 16sp
     * Use for: Card titles, list item primary text
     */
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    /**
     * Title Small - 14sp
     * Use for: Small titles, emphasized labels
     */
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // ===========================
    // BODY STYLES
    // ===========================

    /**
     * Body Large - 16sp
     * Use for: Long-form reading, primary descriptions
     */
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    /**
     * Body Medium - 14sp
     * Use for: Body text, descriptions, list item secondary text
     */
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    /**
     * Body Small - 12sp
     * Use for: Fine print, captions
     */
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // ===========================
    // LABEL STYLES
    // ===========================

    /**
     * Label Large - 14sp
     * Use for: Button text, tab labels
     */
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    /**
     * Label Medium - 12sp
     * Use for: Small button text, chip labels
     */
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    /**
     * Label Small - 11sp
     * Use for: Overline text, timestamps
     */
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
