package com.example.finalexamtest

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    val shirtViewModel: ShirtViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, viewModel = shirtViewModel)
        }

        composable(route = Screen.Insert.route) {
            InsertScreen(navController = navController, viewModel = shirtViewModel)
        }
    }
}
