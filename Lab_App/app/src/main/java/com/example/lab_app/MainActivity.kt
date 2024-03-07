
package com.example.lab_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_app.ui.theme.Lab_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealTracker()
                }
            }
        }
    }
}


@Composable
fun MealTracker() {
    var mealName by remember { mutableStateOf("Meal") }
    var calories by remember { mutableStateOf("0") }
    var mealEntries by remember { mutableStateOf(listOf<String>()) }
    var sortByMeal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(if (sortByMeal) mealEntries.sortedBy { it.split(" - ")[0] } else mealEntries) { entry ->
                    MealEntry(entry)
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = mealName,
                onValueChange = { mealName = it },
                label = { Text("Meal Name", textAlign = TextAlign.Center) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle.Default,
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = calories,
                onValueChange = { calories = it },
                label = { Text("KCal", textAlign = TextAlign.Center) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle.Default,
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Switch(
                    checked = sortByMeal,
                    onCheckedChange = { sortByMeal = it },
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = "Sort by Meal",
                    modifier = Modifier.padding(start = 4.dp).align(Alignment.CenterVertically)
                )
            }

            Button(
                onClick = {
                    if (mealName.isNotEmpty() && calories.isNotEmpty()) {
                        val newEntry = "$mealName - $calories Calories"
                        mealEntries = mealEntries.toMutableList().apply { add(0, newEntry) }
                        mealName = "Meal"
                        calories = "0"
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Add Meal Entry")
            }

        }
    }
}




@Composable
fun MealEntry(entry: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.fire_icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = entry)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab_AppTheme {
        MealTracker()
    }
}
