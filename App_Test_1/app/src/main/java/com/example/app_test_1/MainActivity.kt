// MainActivity.kt
package com.example.app_test_1

import CalorieTrackerScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_test_1.ui.theme.App_Test_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_Test_1Theme {
                CalorieTrackerScreen(viewModel())
            }
        }
    }
}
