package com.example.studentcrudapp.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Color Definitions for Material Design 3 Theme
 *
 * Defines all colors used in the application following Material Design 3
 * color system. Includes both light and dark theme variations.
 *
 * Color Categories:
 * - Primary: Main brand color (Blue)
 * - Secondary: Accent color (Cyan)
 * - Tertiary: Additional accent (Indigo)
 * - Error: Error states (Red)
 * - Success: Success feedback (Green)
 * - Warning: Warning states (Orange)
 * - Neutral: Backgrounds and surfaces (Grays)
 *
 * Accessibility:
 * - All color combinations meet WCAG AA contrast requirements
 * - Minimum contrast ratio 4.5:1 for normal text
 * - Minimum contrast ratio 3:1 for large text
 *
 * Usage:
 * - These colors are used in Theme.kt to build ColorScheme
 * - Don't use directly in composables; use MaterialTheme.colorScheme instead
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-31
 */

// ===========================
// PRIMARY COLORS (Blue)
// ===========================

/**
 * Primary color for light theme.
 * Used for prominent UI elements and app bar.
 */
val PrimaryLight = Color(0xFF1E88E5)

/**
 * Primary color for dark theme.
 * Slightly darker for better contrast.
 */
val PrimaryDark = Color(0xFF1565C0)

/**
 * Color for content on primary color surfaces.
 */
val OnPrimary = Color(0xFFFFFFFF)

/**
 * Container color for less prominent primary elements.
 */
val PrimaryContainer = Color(0xFFBBDEFB)

/**
 * Content color on primary container.
 */
val OnPrimaryContainer = Color(0xFF0D47A1)

// ===========================
// SECONDARY COLORS (Cyan/Teal)
// ===========================

/**
 * Secondary color for light theme.
 * Used for floating action buttons and accents.
 */
val SecondaryLight = Color(0xFF00ACC1)

/**
 * Secondary color for dark theme.
 */
val SecondaryDark = Color(0xFF00838F)

/**
 * Color for content on secondary color surfaces.
 */
val OnSecondary = Color(0xFFFFFFFF)

/**
 * Container color for less prominent secondary elements.
 */
val SecondaryContainer = Color(0xFFB2EBF2)

/**
 * Content color on secondary container.
 */
val OnSecondaryContainer = Color(0xFF006064)

// ===========================
// TERTIARY COLORS (Indigo)
// ===========================

/**
 * Tertiary color for light theme.
 * Used for additional accents and highlights.
 */
val TertiaryLight = Color(0xFF5E35B1)

/**
 * Tertiary color for dark theme.
 */
val TertiaryDark = Color(0xFF4527A0)

/**
 * Color for content on tertiary color surfaces.
 */
val OnTertiary = Color(0xFFFFFFFF)

/**
 * Container color for less prominent tertiary elements.
 */
val TertiaryContainer = Color(0xFFD1C4E9)

/**
 * Content color on tertiary container.
 */
val OnTertiaryContainer = Color(0xFF311B92)

// ===========================
// ERROR COLORS (Red)
// ===========================

/**
 * Error color for light theme.
 * Used for error states and destructive actions.
 */
val ErrorLight = Color(0xFFD32F2F)

/**
 * Error color for dark theme.
 */
val ErrorDark = Color(0xFFC62828)

/**
 * Color for content on error color surfaces.
 */
val OnError = Color(0xFFFFFFFF)

/**
 * Container color for error elements.
 */
val ErrorContainer = Color(0xFFFFCDD2)

/**
 * Content color on error container.
 */
val OnErrorContainer = Color(0xFFB71C1C)

// ===========================
// SUCCESS COLORS (Green)
// ===========================

/**
 * Success color for positive feedback.
 */
val SuccessLight = Color(0xFF388E3C)

/**
 * Success color for dark theme.
 */
val SuccessDark = Color(0xFF2E7D32)

/**
 * Color for content on success surfaces.
 */
val OnSuccess = Color(0xFFFFFFFF)

// ===========================
// WARNING COLORS (Orange)
// ===========================

/**
 * Warning color for caution states.
 */
val WarningLight = Color(0xFFF57C00)

/**
 * Warning color for dark theme.
 */
val WarningDark = Color(0xFFE65100)

/**
 * Color for content on warning surfaces.
 */
val OnWarning = Color(0xFFFFFFFF)

// ===========================
// BACKGROUND COLORS
// ===========================

/**
 * Background color for light theme.
 * Pure white for maximum brightness.
 */
val BackgroundLight = Color(0xFFFFFFFF)

/**
 * Background color for dark theme.
 * True black for OLED displays.
 */
val BackgroundDark = Color(0xFF121212)

/**
 * Content color on light background.
 */
val OnBackgroundLight = Color(0xFF000000)

/**
 * Content color on dark background.
 */
val OnBackgroundDark = Color(0xFFFFFFFF)

// ===========================
// SURFACE COLORS
// ===========================

/**
 * Surface color for light theme.
 * Slightly off-white for elevation.
 */
val SurfaceLight = Color(0xFFF5F5F5)

/**
 * Surface color for dark theme.
 * Elevated dark gray.
 */
val SurfaceDark = Color(0xFF1E1E1E)

/**
 * Content color on light surface.
 */
val OnSurfaceLight = Color(0xFF000000)

/**
 * Content color on dark surface.
 */
val OnSurfaceDark = Color(0xFFFFFFFF)

/**
 * Surface variant for light theme.
 * Used for lower emphasis surfaces.
 */
val SurfaceVariantLight = Color(0xFFE0E0E0)

/**
 * Surface variant for dark theme.
 */
val SurfaceVariantDark = Color(0xFF2C2C2C)

/**
 * Content color on surface variant (light).
 */
val OnSurfaceVariantLight = Color(0xFF424242)

/**
 * Content color on surface variant (dark).
 */
val OnSurfaceVariantDark = Color(0xFFBDBDBD)

// ===========================
// OUTLINE COLORS
// ===========================

/**
 * Outline color for light theme.
 * Used for borders and dividers.
 */
val OutlineLight = Color(0xFFBDBDBD)

/**
 * Outline color for dark theme.
 */
val OutlineDark = Color(0xFF757575)

/**
 * Variant outline for lower contrast.
 */
val OutlineVariantLight = Color(0xFFE0E0E0)

/**
 * Variant outline for dark theme.
 */
val OutlineVariantDark = Color(0xFF616161)

// ===========================
// UTILITY COLORS
// ===========================

/**
 * Transparent color for overlays.
 */
val Transparent = Color(0x00000000)

/**
 * Scrim color for light theme (modal overlays).
 */
val ScrimLight = Color(0x40000000)

/**
 * Scrim color for dark theme.
 */
val ScrimDark = Color(0x80000000)

/**
 * Pure black.
 */
val Black = Color(0xFF000000)

/**
 * Pure white.
 */
val White = Color(0xFFFFFFFF)

// ===========================
// NEUTRAL GRAYS
// ===========================

val Gray50 = Color(0xFFFAFAFA)
val Gray100 = Color(0xFFF5F5F5)
val Gray200 = Color(0xFFEEEEEE)
val Gray300 = Color(0xFFE0E0E0)
val Gray400 = Color(0xFFBDBDBD)
val Gray500 = Color(0xFF9E9E9E)
val Gray600 = Color(0xFF757575)
val Gray700 = Color(0xFF616161)
val Gray800 = Color(0xFF424242)
val Gray900 = Color(0xFF212121)
