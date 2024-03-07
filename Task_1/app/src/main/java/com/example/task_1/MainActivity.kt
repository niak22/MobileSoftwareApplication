package com.example.task_1

//import androidx.compose.material.icons.Icons
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.task_1.ui.theme.Task_1Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Define a data class to represent a meal
data class Meal(val name: String, val calories: Int)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieManagerApp() {
    val navController = rememberNavController()
    val mealListState = remember { mutableStateOf<List<Meal>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Calorie Manager",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(16.dp)
            ) {
                NavigationButtons(navController = navController)
            }
        },
        content = {
            Navigation(navController = navController, mealListState = mealListState)
        }
    )
}

@Composable
fun NavigationButtons(navController: NavHostController) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "today",
            onClick = {
                navController.navigate("today")
            },
            icon = { Icon(Icons.Default.CalendarToday, contentDescription = "Today") },
            label = { Text("Today") }
        )
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "home",
            onClick = {
                navController.navigate("home")
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "history",
            onClick = {
                navController.navigate("history")
            },
            icon = { Icon(Icons.Default.History, contentDescription = "History") },
            label = { Text("History") }
        )
    }
}

@Composable
fun Navigation(navController: NavHostController, mealListState: MutableState<List<Meal>>) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController, mealList = mealListState.value) { meal ->
                // Update meal list state
                mealListState.value = mealListState.value.toMutableList().apply { add(meal) }
            }
        }
        composable("today") {
            TodayScreen(mealList = mealListState.value) { meal ->
                // Update meal list state
                mealListState.value = mealListState.value.toMutableList().apply { add(meal) }
            }
        }
        composable("history") {
            HistoryScreen(mealList = mealListState.value, navController = navController)
        }
        composable("addMeal") {
            AddMealScreen(navController = navController, mealList = mealListState.value) { meal ->
                // Update meal list state
                mealListState.value = mealListState.value.toMutableList().apply { add(meal) }
            }
        }
        composable("dayDetail/{dayOfWeek}") { backStackEntry ->
            // Extract the dayOfWeek argument from the route
            val dayOfWeek = backStackEntry.arguments?.getString("dayOfWeek")
            if (dayOfWeek != null) {
                val mealsForDay = mealListState.value.filter { getDayOfWeek(it) == dayOfWeek }
                DayDetailScreen(navController = navController, meals = mealsForDay)
            } else {
                // Handle error case where dayOfWeek argument is missing
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController, mealList: List<Meal>, onMealAdded: (Meal) -> Unit) {
    var totalCalories by remember { mutableStateOf(0) }

    // Calculate total calories
    totalCalories = mealList.sumBy { it.calories }

    // Calculate percentage of calories consumed (assuming a daily goal of 2000 calories)
    val percentage = (totalCalories.toFloat() / 2000) * 100

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Display the overview
        Text(
            text = "Meals: ${mealList.size}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = " $totalCalories / 2000",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = " $percentage%",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Add a button to add a new meal
        Button(
            onClick = {
                navController.navigate("addMeal")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add Meal",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TodayScreen(mealList: List<Meal>, onMealAdded: (Meal) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Today's Screen",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        // Display the list of meals
        mealList.forEach { meal ->
            Text(
                text = "${meal.name}: ${meal.calories} calories",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        // Add a button to add a new meal
        Button(
            onClick = {
                // Simulate adding a meal
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "New Day",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AddMealScreen(navController: NavHostController, mealList: List<Meal>, onMealAdded: (Meal) -> Unit) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var showEmptyNameError by remember { mutableStateOf(false) }
    var showZeroCaloriesError by remember { mutableStateOf(false) }
    var showNameExistsError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add Meal Screen",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Input field for Meal Name
        OutlinedTextField(
            value = mealName,
            onValueChange = {
                mealName = it
                showEmptyNameError = false
                showNameExistsError = false
            },
            label = { Text("Meal Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Input field for Calories
        OutlinedTextField(
            value = calories,
            onValueChange = {
                calories = it
                showZeroCaloriesError = false
            },
            label = { Text("Calories") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                // Validate mealName and calories
                val validMealName = mealName.isNotEmpty()
                val validCalories = calories.isNotEmpty() && calories.toIntOrNull() ?: 0 > 0
                val mealNameExists = mealList.any { it.name == mealName }

                if (validMealName && validCalories) {
                    if (mealNameExists) {
                        // Show error - Meal name already exists
                        showNameExistsError = true
                    } else {
                        // Create a new meal
                        val meal = Meal(mealName, calories.toInt())
                        // Call the onMealAdded callback to add the meal
                        onMealAdded(meal)
                        // Navigate back to Home screen
                        navController.navigateUp()
                    }
                } else {
                    // Show error - Calories must be >0
                    showZeroCaloriesError = true
                }
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Error messages
        if (showEmptyNameError || showZeroCaloriesError || showNameExistsError) {
            val errorMessage = buildString {
                if (showEmptyNameError) appendLine("Meal name cannot be empty")
                if (showZeroCaloriesError) appendLine("Calories must be > 0")
                if (showNameExistsError) appendLine("Meal name already exists")
            }
            Text(
                text = errorMessage.trim(),
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
        }
    }
}

@Composable
fun HistoryScreen(mealList: List<Meal>, navController: NavHostController) {
    // Group meals by day of the week
    val mealsByDayOfWeek = mealList.groupBy { getDayOfWeek(it) }

    // Get all days of the week
    val allDaysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "History Screen",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Display buttons for each day of the week
        allDaysOfWeek.forEach { dayOfWeek ->
            val meals = mealsByDayOfWeek[dayOfWeek]
            Button(
                onClick = {
                    navController.navigate("dayDetail/$dayOfWeek")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "$dayOfWeek: - ${meals?.sumBy { it.calories } ?: 0} Kcal",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DayDetailScreen(navController: NavHostController, meals: List<Meal>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Calorie Manager") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Meals for this day",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            // Display the list of meals
            meals.forEach { meal ->
                Text(
                    text = "${meal.name}: ${meal.calories} calories",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// Helper function to get the day of the week from a Meal
private fun getDayOfWeek(meal: Meal): String {
    val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dateFormat.format(Date())
}


@Preview(showBackground = true)
@Composable
fun PreviewCalorieManagerApp() {
    Task_1Theme {
        CalorieManagerApp()
    }
}
