package com.example.app_test_2

import AppTheme
import CalorieTrackerViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalorieTrackerScreen(viewModel: CalorieTrackerViewModel = viewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Day KCal",
            style = MaterialTheme.typography.headlineLarge
        )
        MealEntryCard(viewModel)
    }
}

@Composable
fun MealEntryCard(viewModel: CalorieTrackerViewModel) {
    // Implement the UI for entering meal names and calories
}

@Preview(showBackground = true)
@Composable
fun PreviewCalorieTrackerScreen() {
    AppTheme {
        CalorieTrackerScreen()
    }
}
