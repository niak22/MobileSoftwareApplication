import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // Add this import


class CalorieTrackerViewModel : ViewModel() {
    private val _meals = mutableStateListOf<Meal>()

    fun addMeal(meal: Meal) {
        _meals.add(meal)
    }

    fun clearMeals() {
        _meals.clear()
    }

    fun getTotalCalories(): Int {
        return _meals.sumBy { it.calories }
    }

    fun getMeals(): List<Meal> {
        return _meals.toList()
    }
}

