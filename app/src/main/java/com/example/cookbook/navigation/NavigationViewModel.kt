package com.example.cookbook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.Dining
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cookbook.navigation.util.MenuItem

class NavigationViewModel: ViewModel() {

    val menuOptions = listOf(
        MenuItem(
            selectedIcon = Icons.Default.Dining,
            unselectedIcon = Icons.Outlined.Dining,
            label = "My Recipes"
        ),
        MenuItem(
            selectedIcon = Icons.Default.Explore,
            unselectedIcon = Icons.Outlined.Explore,
            label = "Explore"
        ),
        MenuItem(
            selectedIcon = Icons.Default.ShoppingBasket,
            unselectedIcon = Icons.Outlined.ShoppingBasket,
            label = "Shopping"
        ),
        MenuItem(
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            label = "Template"
        )
    )

    var isCreatingRecipe by mutableStateOf(false)
        private set

    var selectedIndex by mutableIntStateOf(0)
        private set

    fun onSelectedItem(index: Int) {
        selectedIndex = index
    }

    fun toggleRecipeCreatingDialog() {
        isCreatingRecipe = !isCreatingRecipe
    }
}