import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MealEntryCard(viewModel: CalorieTrackerViewModel) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = mealName,
            onValueChange = { mealName = it },
            label = { Text("Meal Name") }
        )
        OutlinedTextField(
            value = calories,
            onValueChange = { calories = it },
            label = { Text("Calories") }
        )
        Button(
            onClick = {
                if (mealName.isNotEmpty() && calories.isNotEmpty()) {
                    viewModel.addMeal(Meal(mealName, calories.toInt()))
                    mealName = ""
                    calories = ""
                }
            }
        ) {
            Text("Enter")
        }
    }
}
