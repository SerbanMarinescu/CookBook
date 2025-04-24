package com.example.cookbook.feature_recipe_book.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Step",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = arrayOf("recipeId"),
            childColumns = arrayOf("recipeId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val stepId: Long = 0,
    val recipeId: String,
    val stepNumber: Int,
    val instructions: String
)
