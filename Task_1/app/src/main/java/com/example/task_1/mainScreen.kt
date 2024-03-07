package com.example.task_1

import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

fun MainApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val currentScreen
}