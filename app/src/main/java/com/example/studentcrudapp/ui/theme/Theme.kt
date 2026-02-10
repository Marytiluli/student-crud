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
 * Material Design 3 Theme Configuration
 *
 * Provides the complete theming system for the application including:
 * - Light and dark color schemes
 * - Dynamic colors (Material You) on Android 12+
 * - Typography system
 * - Shape system
 * - System UI styling (status bar, navigation bar)
 *
 * Features:
 * - Automatic dark mode based on system settings
 * - Dynamic color extraction from wallpaper (Android 12+)
 * - Manual theme override support (future)
 * - Consistent styling across all screens
 *
 * Architecture:
 * - Uses CompositionLocalProviders for theme propagation
 * - Follows Material Design 3 guidelines
 * - Supports edge-to-edge display
 *
 * @author Mary Tiluli
 * @version 1.0.0
 * @since 2026-01-31
 */

/**
 * Dark color scheme configuration.
 * Applied when system is in dark mode or user selects dark theme.
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = SecondaryDark,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,

    tertiary = TertiaryDark,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,

    error = ErrorDark,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    background = BackgroundDark,
    onBackground = OnBackgroundDark,

    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,

    outline = OutlineDark,
    outlineVariant = OutlineVariantDark,

    scrim = ScrimDark
)

/**
 * Light color scheme configuration.
 * Applied when system is in light mode or user selects light theme.
 */
private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = SecondaryLight,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,

    tertiary = TertiaryLight,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,

    error = ErrorLight,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    background = BackgroundLight,
    onBackground = OnBackgroundLight,

    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,

    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,

    scrim = ScrimLight
)

/**
 * Main theme composable that wraps the entire app.
 *
 * Automatically applies:
 * - Appropriate color scheme based on system theme
 * - Dynamic colors on Android 12+ (Material You)
 * - Typography and shape systems
 * - System UI bar colors
 *
 * @param darkTheme Whether to use dark theme (defaults to system setting)
 * @param dynamicColor Whether to use dynamic colors from wallpaper (Android 12+ only)
 * @param content Composable content to be themed
 */
@Composable
fun StudentCrudAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Determine which color scheme to use
    val colorScheme = when {
        // Use dynamic color scheme on Android 12+ if enabled
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }
        // Use static dark theme
        darkTheme -> DarkColorScheme
        // Use static light theme
        else -> LightColorScheme
    }

    // Get current view for system UI styling
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            // Set status bar and navigation bar appearance
            val window = (view.context as Activity).window

            // Set status bar color to transparent for edge-to-edge
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            window.navigationBarColor = android.graphics.Color.TRANSPARENT

            // Set status bar icons color based on theme
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    // Apply Material Theme with all systems
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
