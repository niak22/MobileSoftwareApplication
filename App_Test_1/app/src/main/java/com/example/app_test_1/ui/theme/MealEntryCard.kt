import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealEntryCard(viewModel: CalorieTrackerViewModel) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = mealName,
            onValueChange = { mealName = it },
            label = { Text("Meal Name") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = calories,
            onValueChange = { calories = it },
            label = { Text("Calories") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                if (mealName.isNotBlank() && calories.isNotBlank()) {
                    viewModel.addMeal(Meal(mealName, calories.toInt()))
                    mealName = ""
                    calories = ""
                }
            }
        ) {
            Text(text = "Enter")
        }
    }
}
