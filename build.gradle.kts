/**
 * Root-level Build Configuration
 *
 * This build file configures project-wide settings and manages
 * plugin versions for all modules in the application.
 *
 * Key Features:
 * - Kotlin version management
 * - Android Gradle Plugin configuration
 * - Compose Compiler version
 *
 * @author Mary Tiluli
 * @version 1.0.0
 */

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
