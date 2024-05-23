package com.example.employeedirectory.core.navigation

sealed class Screen(val route: String) {
    data object DirectoryScreen : Screen("directory_screen")
}