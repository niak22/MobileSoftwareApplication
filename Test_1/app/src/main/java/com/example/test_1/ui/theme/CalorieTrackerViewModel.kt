// CalorieTrackerViewModel.kt

import androidx.lifecycle.ViewModel
import com.example.test_1.ui.theme.Meal



class CalorieTrackerViewModel : ViewModel() {
    private val _meals = mutableListOf<Meal>()

    fun addMeal(meal: Meal) {
        _meals.add(meal)
    }

    fun getMeals(): List<Meal> {
        return _meals.toList()
    }
}
