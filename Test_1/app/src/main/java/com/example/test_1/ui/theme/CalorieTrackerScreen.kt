//CalorieTrackerScreen.kt

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.app_test_1.MealEntryCard
import com.example.test_1.ui.theme.Meal

@Composable
fun DayCaloriesHeader(meals: List<Meal>) {
    // Calculate total calories
    val totalCalories = meals.sumBy { it.calories }
    val percentage = String.format("%.2f", (totalCalories.toFloat() / 2000f) * 100)

    Column {
        Text(
            text = "Day KCal",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "$totalCalories/2000 ($percentage%)",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun CalorieTrackerScreen(viewModel: CalorieTrackerViewModel) {
    // Retrieve meals from view model
    val meals = viewModel.getMeals()

    Column {
        DayCaloriesHeader(meals)
        MealEntryCard(viewModel = viewModel)
    }
}
