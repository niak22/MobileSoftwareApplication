import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

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
