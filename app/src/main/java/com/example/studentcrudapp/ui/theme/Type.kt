package com.example.studentcrudapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Typography System for Material Design 3
 *
 * Defines the complete type scale following Material Design 3 guidelines.
 * Uses default system fonts for maximum compatibility and performance.
 *
 * Type Scale (13 styles):
 * - Display: Extra large text (hero sections)
 * - Headline: Large text (titles, headers)
 * - Title: Medium text (section titles)
 * - Body: Regular text (content, paragraphs)
 * - Label: Small text (buttons, labels)
 *
 * Font Weights:
 * - Normal: 400 (regular text)
 * - Medium: 500 (slightly emphasized)
 * - Bold: 700 (headings, emphasis)
 *
 * Line Heights:
 * - Designed for optimal readability
 * - Based on Material Design recommendations
 * - Ensures proper vertical rhythm
 *
 * Accessibility:
 * - Minimum font size: 12sp (label small)
 * - Clear hierarchy for screen readers
 * - Proper contrast with background
 *
 * Usage:
 * ```
 * Text(
 *     text = "Hello World",
 *     style = MaterialTheme.typography.headlineMedium
 * )
 * ```
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-31
 */
val Typography = Typography(
    // ===========================
    // DISPLAY (Extra Large Text)
    // ===========================

    /**
     * Display Large - Largest text style.
     * Use for hero sections or splash screens.
     * 57sp / 64sp line height
     */
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),

    /**
     * Display Medium.
     * 45sp / 52sp line height
     */
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Display Small.
     * 36sp / 44sp line height
     */
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    // ===========================
    // HEADLINE (Large Text)
    // ===========================

    /**
     * Headline Large.
     * Use for main screen titles.
     * 32sp / 40sp line height
     */
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Headline Medium.
     * Common for screen titles and card headers.
     * 28sp / 36sp line height
     */
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Headline Small.
     * 24sp / 32sp line height
     */
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // ===========================
    // TITLE (Medium Text)
    // ===========================

    /**
     * Title Large.
     * Use for dialog titles and section headers.
     * 22sp / 28sp line height
     */
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    /**
     * Title Medium.
     * Common for list item titles.
     * 16sp / 24sp line height
     */
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    /**
     * Title Small.
     * Use for subtitles and secondary titles.
     * 14sp / 20sp line height
     */
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // ===========================
    // BODY (Regular Text)
    // ===========================

    /**
     * Body Large.
     * Main body text for important content.
     * 16sp / 24sp line height
     */
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    /**
     * Body Medium.
     * Standard body text for most content.
     * 14sp / 20sp line height
     */
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    /**
     * Body Small.
     * Smaller body text for less important content.
     * 12sp / 16sp line height
     */
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // ===========================
    // LABEL (Small Text)
    // ===========================

    /**
     * Label Large.
     * Use for button text and prominent labels.
     * 14sp / 20sp line height
     */
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    /**
     * Label Medium.
     * Standard label text.
     * 12sp / 16sp line height
     */
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    /**
     * Label Small.
     * Smallest text for captions and overlines.
     * 11sp / 16sp line height
     */
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
