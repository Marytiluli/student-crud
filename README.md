# Student Manager - Android CRUD Application

A modern Android application for managing student records, built with Jetpack Compose, Room Database, and MVVM architecture. Features a clean Material Design 3 interface with support for light/dark themes and dynamic colors.

---

## Project Overview

Student Manager is a comprehensive student information management system that allows users to:

* Create, read, update, and delete student records
* Search and filter students in real-time
* View detailed student statistics
* Enjoy a modern, responsive UI with Material You design
* Switch between light and dark themes
* Backup and export data
* Multi-language support (English & Swahili)

---

## Features

### Week 1: Foundation (Completed)

* Basic CRUD operations (Create, Read, Update, Delete)
* Real-time search functionality
* Material Design 3 UI with dynamic colors
* Room database with migrations
* MVVM architecture with Repository pattern
* Jetpack Compose UI
* Input validation
* Empty states and loading indicators
* Unit tests and instrumented tests

### Week 2–4: Coming Soon

* Course management system
* Filtering and sorting options
* Settings screen with preferences
* Export to CSV functionality
* Database backup and restore
* Student detail screen with statistics
* Grade management
* Attendance tracking
* Analytics dashboard
* Profile photos
* QR code student ID cards
* Voice input

---

## Tech Stack

| Technology         | Version | Purpose                  |
| ------------------ | ------- | ------------------------ |
| Kotlin             | 1.9.22  | Programming language     |
| Jetpack Compose    | 1.6.1   | Modern UI toolkit        |
| Material Design 3  | 1.2.0   | Design system            |
| Room Database      | 2.6.1   | Local data persistence   |
| Coroutines         | 1.7.3   | Asynchronous programming |
| Flow               | 1.7.3   | Reactive data streams    |
| Navigation Compose | 2.7.7   | In-app navigation        |
| DataStore          | 1.0.0   | Preferences storage      |
| Coil               | 2.6.0   | Image loading            |
| ZXing              | 3.5.3   | QR code generation       |
| JUnit              | 4.13.2  | Unit testing             |
| Espresso           | 3.5.1   | UI testing               |

---

## Project Structure

```
StudentCrudApp/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/studentcrudapp/
│           │   ├── data/
│           │   │   ├── dao/
│           │   │   ├── database/
│           │   │   ├── entity/
│           │   │   └── repository/
│           │   ├── ui/
│           │   │   ├── components/
│           │   │   ├── screens/
│           │   │   ├── theme/
│           │   │   └── navigation/
│           │   ├── viewmodel/
│           │   ├── utils/
│           │   └── MainActivity.kt
│           └── res/
│
├── test/
├── androidTest/
└── build.gradle.kts
```

---

## Getting Started

### Prerequisites

* Android Studio Hedgehog (2023.1.1) or newer
* JDK 17 or higher
* Android SDK (Min API 24)
* Kotlin 1.9.22

### Installation

1. Clone the repository

```
git clone https://github.com/Marytiluli/student-crud.git
cd StudentCrudApp
```

2. Open in Android Studio

* Select Open an Existing Project
* Navigate to the cloned folder
* Wait for Gradle sync

3. Run the application

* Connect a device or start emulator
* Click Run button

### Build Variants

* Debug: Development build
* Release: Production build with ProGuard

---

## Architecture

This project follows MVVM (Model–View–ViewModel) with Repository pattern.

UI Layer (Compose)

* Screens
* Components
* Theme

ViewModel Layer

* UI state management
* Business logic
* Event handling

Repository Layer

* Single source of truth
* Data abstraction

Data Layer

* Room Database (DAO)
* Preferences (DataStore)
* Network (future)

### Key Design Patterns

* MVVM
* Repository
* Factory
* Singleton
* Observer (Flow)

---

## Testing

Run Unit Tests

```
./gradlew test
```

Run Instrumented Tests

```
./gradlew connectedAndroidTest
```

Test Coverage

* ViewModel
* Repository
* Utilities
* Database
* UI interactions

---

## Building for Release

Generate signed APK or AAB

```
./gradlew assembleRelease
```

Outputs

* APK: app/build/outputs/apk/release/app-release.apk
* AAB: app/build/outputs/bundle/release/app-release.aab

---

## Contributing

* Fork the repository
* Create feature branch
* Commit changes
* Push to branch
* Open Pull Request

---

## License

MIT License

---

## Author

Mary Tiluli
Email: [marytiluli@gmail.com](mailto:marytiluli@gmail.com)
GitHub: @marytiluli

---

## Acknowledgments

* Material Design 3
* Android Developers
* Jetpack Compose
* Android Community

---

## Project Stats

Version: 1.0.0
Min SDK: 24
Target SDK: 34
Language: Kotlin

---

Star this repository if you find it helpful.
