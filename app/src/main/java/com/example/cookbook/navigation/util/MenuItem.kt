package com.example.cookbook.navigation.util

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
)
