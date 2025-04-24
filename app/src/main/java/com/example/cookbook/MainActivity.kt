package com.example.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.navigation.Navigation
import com.example.cookbook.navigation.NavigationViewModel
import com.example.cookbook.ui.theme.CookBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookBookTheme {
                val navController = rememberNavController()
                val viewModel = viewModel<NavigationViewModel>()

                Navigation(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}


