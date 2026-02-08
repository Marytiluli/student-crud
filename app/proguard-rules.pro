# Student CRUD App - ProGuard Rules
# Optimizes and obfuscates release builds while preserving necessary classes
#
# @author Mary Tiluli
# @version 1.0.0

# Keep source file names and line numbers for better crash reports
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep custom exceptions
-keep public class * extends java.lang.Exception

# Room Database - Keep entity classes and DAOs
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep all DAO interfaces
-keep interface * extends androidx.room.Dao {
    *;
}

# Preserve Room annotations
-keepattributes *Annotation*

# Kotlin Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# DataStore
-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}

# Compose
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }

# Keep ViewModel classes
-keep class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
-keep class * extends androidx.lifecycle.AndroidViewModel {
    <init>(...);
}

# Material Design 3
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# Coil image loading
-keep class coil.** { *; }
-dontwarn coil.**

# ZXing QR codes
-keep class com.google.zxing.** { *; }
-dontwarn com.google.zxing.**

# Keep data classes (for JSON serialization)
-keepclassmembers class com.example.studentcrudapp.data.entity.** {
    <fields>;
    <init>(...);
}

# Keep repository classes
-keep class com.example.studentcrudapp.data.repository.** { *; }

# Keep ViewModels
-keep class com.example.studentcrudapp.viewmodel.** { *; }

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Optimize
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
