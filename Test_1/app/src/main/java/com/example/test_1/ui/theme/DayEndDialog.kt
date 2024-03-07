// DayEndDialog.kt
package com.example.app_test_1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test_1.ui.theme.Meal

@Composable
fun DayEndDialog(meals: List<Meal>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Day End Summary") },
        text = {
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                meals.forEach { meal ->
                    Text("${meal.name}: ${meal.calories} kcal")
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onDismiss) {
                    Text("New Day")
                }
                Button(onClick = onDismiss) {
                    Text("Exit")
                }
            }
        }
    )
}
