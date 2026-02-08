/**
 * Student CRUD Application - Settings Configuration
 *
 * This file configures the root project settings including:
 * - Plugin management repositories
 * - Dependency resolution management
 * - Project structure definition
 *
 * @author Mary Tiluli
 * @version 1.0.0
 */

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "StudentCrudApp"
include(":app")
