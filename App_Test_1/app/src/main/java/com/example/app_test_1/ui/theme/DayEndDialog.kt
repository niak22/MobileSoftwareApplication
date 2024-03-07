import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DayEndDialog(viewModel: CalorieTrackerViewModel, onClose: () -> Unit) {
    val totalCalories = viewModel.getTotalCalories()
    val meals = viewModel.getMeals()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Day Total: $totalCalories/2000 (${(totalCalories.toDouble() / 2000 * 100).toInt()}%)",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn {
            items(meals) { meal ->
                Text(text = "${meal.name}: ${meal.calories} kcal", modifier = Modifier.padding(bottom = 4.dp))
            }
        }
        Button(
            onClick = onClose,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Exit")
        }
    }
}
