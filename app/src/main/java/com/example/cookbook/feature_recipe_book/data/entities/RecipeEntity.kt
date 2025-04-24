package com.example.cookbook.feature_recipe_book.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Duration

@Entity(tableName = "Recipe")
data class RecipeEntity(
    @PrimaryKey
    val recipeId: String,
    val title: String,
    val description: String,
    val servings: Int,
    val duration: Duration,
    val imageUri: String?
)
