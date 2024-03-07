// MealEntryCard.kt
package com.example.app_test_1

import CalorieTrackerViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.test_1.ui.theme.Meal


@Composable
fun MealEntryCard(viewModel: CalorieTrackerViewModel) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var meals by remember { mutableStateOf(emptyList<Meal>()) }
    var totalCalories by remember { mutableStateOf(0) }
    var percentage by remember { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = mealName,
            onValueChange = { mealName = it },
            label = { Text("Meal Name") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        OutlinedTextField(
            value = calories,
            onValueChange = { calories = it },
            label = { Text("Calories") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (mealName.isNotEmpty() && calories.isNotEmpty()) {
                        viewModel.addMeal(Meal(mealName, calories.toInt()))
                        mealName = ""
                        calories = ""
                        meals = viewModel.getMeals() // Update the meals list
                    }
                }
            ),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Button(
            onClick = {
                if (mealName.isNotEmpty() && calories.isNotEmpty()) {
                    viewModel.addMeal(Meal(mealName, calories.toInt()))
                    mealName = ""
                    calories = ""
                    meals = viewModel.getMeals() // Update the meals list
                }
            },
            enabled = mealName.isNotEmpty() && calories.isNotEmpty(),
            modifier = Modifier.padding(8.dp)
        ) {
            Text("End Day")
        }

        if (meals.isNotEmpty()) {
            DayEndDialog(meals = meals, onDismiss = { meals = emptyList() })
        }
    }

    // Update total calorie and percentage
    LaunchedEffect(meals) {
        totalCalories = meals.sumBy { it.calories }
        percentage = (totalCalories.toFloat() / 2000) * 100
    }

}
