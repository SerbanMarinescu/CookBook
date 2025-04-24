package com.example.cookbook.feature_recipe_book.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.cookbook.feature_recipe_book.util.Category

@Entity
data class CategoryEntity(
    @PrimaryKey
    val category: Category
)

@Entity(
    primaryKeys = ["recipeId","category"],
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class RecipeCategoryCrossRef(
    val recipeId: String,
    val category: Category
)