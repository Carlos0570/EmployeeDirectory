package com.example.employeedirectory.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.employeedirectory.directory.DirectoryScreen
import com.example.employeedirectory.directory.DirectoryViewModel

@Composable
fun DirectoryNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.DirectoryScreen.route) {

        composable(route = Screen.DirectoryScreen.route) {
            val directoryViewmodel = hiltViewModel<DirectoryViewModel>()
            DirectoryScreen(directoryViewmodel)
        }
    }
}