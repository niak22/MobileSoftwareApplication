import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DayCaloriesHeader(totalCalories: Int) {
    Column {
        Text(
            text = "Day KCal",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "$totalCalories/2000",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun CalorieTrackerScreen(viewModel: CalorieTrackerViewModel) {
    // Logic to calculate total calories
    val totalCalories = viewModel.getTotalCalories()

    Column {
        DayCaloriesHeader(totalCalories = totalCalories)
        MealEntryCard(viewModel = viewModel)
    }
}
