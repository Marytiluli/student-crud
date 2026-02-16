package com.example.studentcrudapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Theme - Material Design 3 Theme Implementation
 *
 * Implements the complete Material You theme system with:
 * - Light and dark color schemes
 * - Dynamic colors (Android 12+)
 * - Custom color fallback (Android 11 and below)
 * - Typography scale
 * - Shape system
 * - Edge-to-edge support
 *
 * Features:
 * - Automatic dark mode detection
 * - System status bar color management
 * - Dynamic color extraction from wallpaper (Android 12+)
 * - Backward compatibility for older Android versions
 *
 * Dynamic Colors:
 * - Android 12+ uses Material You dynamic theming
 * - Colors adapt to user's wallpaper
 * - Provides personalized experience
 *
 * @param darkTheme Whether to use dark theme (auto-detected from system)
 * @param dynamicColor Whether to use dynamic colors (Android 12+)
 * @param content Composable content to apply theme to
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-02-05
 */

/**
 * Dark color scheme for the application.
 * Used when system is in dark mode or user selects dark theme.
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = SecondaryDark,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainer,

    tertiary = TertiaryDark,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainer,

    error = ErrorDark,
    onError = OnError,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainer,

    background = BackgroundDark,
    onBackground = OnBackgroundDark,

    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,

    outline = OutlineDark,
    outlineVariant = OutlineVariantDark
)

/**
 * Light color scheme for the application.
 * Used when system is in light mode (default).
 */
private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,

    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,

    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    background = Background,
    onBackground = OnBackground,

    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,

    outline = Outline,
    outlineVariant = OutlineVariant
)

/**
 * Main theme composable for the application.
 *
 * Applies Material Design 3 theming with:
 * - Color scheme (light/dark/dynamic)
 * - Typography scale
 * - Shape system
 * - System bar colors
 *
 * @param darkTheme Whether to use dark theme (defaults to system setting)
 * @param dynamicColor Whether to enable dynamic colors (defaults to true on Android 12+)
 * @param content Content to apply theme to
 */
@Composable
fun StudentCrudAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    /**
     * Color scheme selection logic:
     * 1. If Android 12+ and dynamic colors enabled:
     *    - Use system dynamic colors
     * 2. Else:
     *    - Use custom light/dark color scheme
     */
    val colorScheme = when {
        // Dynamic colors on Android 12+
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        // Dark theme on older Android versions
        darkTheme -> DarkColorScheme

        // Light theme (default)
        else -> LightColorScheme
    }

    /**
     * Update system bars to match theme.
     * Makes status bar and navigation bar transparent with proper icon colors.
     */
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()

            // Set system bar icon colors based on theme
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    /**
     * Apply Material Theme with color scheme, typography, and shapes.
     */
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
