package com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen

import android.net.Uri
import com.example.cookbook.feature_recipe_book.domain.model.Ingredient
import com.example.cookbook.feature_recipe_book.domain.model.Step
import kotlin.time.Duration

data class RecipeState(
    val recipeTitle: String = "",
    val recipeDescription: String = "",
    val servings: Int = 0,
    val recipeDuration: Duration = Duration.ZERO,
    val imageUri: Uri? = null,
    val currentStep: Int = 1,
    val ingredients: List<Ingredient> = emptyList(),
    val steps: List<Step> = emptyList()
)
