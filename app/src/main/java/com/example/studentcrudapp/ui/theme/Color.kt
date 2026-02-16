package com.example.studentcrudapp.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Color Palette - Material Design 3 Colors
 *
 * Defines the color palette for the application theme.
 * Supports both light and dark color schemes.
 *
 * Color System:
 * - Primary: Main brand color (Blue)
 * - Secondary: Accent color (Cyan)
 * - Tertiary: Supporting color (Purple)
 * - Error: Error states (Red)
 * - Background: Screen backgrounds
 * - Surface: Card and container surfaces
 *
 * Dynamic Color Support:
 * - Android 12+ uses Material You dynamic colors
 * - Older versions use these defined colors
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-05
 */

// ===========================
// PRIMARY COLORS - Blue
// ===========================

/** Primary color - Main brand color */
val Primary = Color(0xFF1E88E5)

/** Primary color for dark theme */
val PrimaryDark = Color(0xFF1565C0)

/** Container color for primary elements */
val PrimaryContainer = Color(0xFFBBDEFB)

/** Container color for primary elements in dark theme */
val PrimaryContainerDark = Color(0xFF0D47A1)

/** Text/icon color on primary background */
val OnPrimary = Color(0xFFFFFFFF)

/** Text/icon color on primary container */
val OnPrimaryContainer = Color(0xFF003258)

// ===========================
// SECONDARY COLORS - Cyan
// ===========================

/** Secondary color - Accent color */
val Secondary = Color(0xFF00ACC1)

/** Secondary color for dark theme */
val SecondaryDark = Color(0xFF00838F)

/** Container color for secondary elements */
val SecondaryContainer = Color(0xFFB2EBF2)

/** Container color for secondary elements in dark theme */
val SecondaryContainerDark = Color(0xFF006064)

/** Text/icon color on secondary background */
val OnSecondary = Color(0xFFFFFFFF)

/** Text/icon color on secondary container */
val OnSecondaryContainer = Color(0xFF002022)

// ===========================
// TERTIARY COLORS - Purple
// ===========================

/** Tertiary color - Supporting accent */
val Tertiary = Color(0xFF5E35B1)

/** Tertiary color for dark theme */
val TertiaryDark = Color(0xFF4527A0)

/** Container color for tertiary elements */
val TertiaryContainer = Color(0xFFD1C4E9)

/** Container color for tertiary elements in dark theme */
val TertiaryContainerDark = Color(0xFF311B92)

/** Text/icon color on tertiary background */
val OnTertiary = Color(0xFFFFFFFF)

/** Text/icon color on tertiary container */
val OnTertiaryContainer = Color(0xFF1A0033)

// ===========================
// ERROR COLORS - Red
// ===========================

/** Error color - For error states */
val Error = Color(0xFFD32F2F)

/** Error color for dark theme */
val ErrorDark = Color(0xFFC62828)

/** Container color for error elements */
val ErrorContainer = Color(0xFFFFCDD2)

/** Container color for error elements in dark theme */
val ErrorContainerDark = Color(0xFFB71C1C)

/** Text/icon color on error background */
val OnError = Color(0xFFFFFFFF)

/** Text/icon color on error container */
val OnErrorContainer = Color(0xFF410002)

// ===========================
// BACKGROUND COLORS
// ===========================

/** Background color for light theme */
val Background = Color(0xFFFAFAFA)

/** Background color for dark theme */
val BackgroundDark = Color(0xFF121212)

/** Text/icon color on background (light theme) */
val OnBackground = Color(0xFF1C1B1F)

/** Text/icon color on background (dark theme) */
val OnBackgroundDark = Color(0xFFE6E1E5)

// ===========================
// SURFACE COLORS
// ===========================

/** Surface color for light theme */
val Surface = Color(0xFFFFFFFF)

/** Surface color for dark theme */
val SurfaceDark = Color(0xFF1C1B1F)

/** Text/icon color on surface (light theme) */
val OnSurface = Color(0xFF1C1B1F)

/** Text/icon color on surface (dark theme) */
val OnSurfaceDark = Color(0xFFE6E1E5)

/** Surface variant for subtle differences */
val SurfaceVariant = Color(0xFFE7E0EC)

/** Surface variant for dark theme */
val SurfaceVariantDark = Color(0xFF49454F)

/** Text/icon color on surface variant (light) */
val OnSurfaceVariant = Color(0xFF49454F)

/** Text/icon color on surface variant (dark) */
val OnSurfaceVariantDark = Color(0xFFCAC4D0)

// ===========================
// OUTLINE COLORS
// ===========================

/** Outline color for borders (light theme) */
val Outline = Color(0xFF79747E)

/** Outline color for borders (dark theme) */
val OutlineDark = Color(0xFF938F99)

/** Outline variant for subtle borders */
val OutlineVariant = Color(0xFFCAC4D0)

/** Outline variant for dark theme */
val OutlineVariantDark = Color(0xFF49454F)

// ===========================
// CUSTOM COLORS
// ===========================

/** Success color - For success states */
val Success = Color(0xFF388E3C)

/** Success color for dark theme */
val SuccessDark = Color(0xFF2E7D32)

/** Warning color - For warning states */
val Warning = Color(0xFFF57C00)

/** Warning color for dark theme */
val WarningDark = Color(0xFFE65100)

/** Info color - For informational states */
val Info = Color(0xFF1976D2)

/** Info color for dark theme */
val InfoDark = Color(0xFF0D47A1)

// ===========================
// SEMANTIC COLORS
// ===========================

/** Color for high priority items */
val HighPriority = Color(0xFFD32F2F)

/** Color for medium priority items */
val MediumPriority = Color(0xFFF57C00)

/** Color for low priority items */
val LowPriority = Color(0xFF388E3C)

/** Color for completed items */
val Completed = Color(0xFF4CAF50)

/** Color for pending items */
val Pending = Color(0xFFFF9800)

/** Color for cancelled items */
val Cancelled = Color(0xFF757575)
