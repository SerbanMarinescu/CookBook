package com.example.cookbook.feature_recipe_book.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithDetailsEntity(
    @Embedded
    val recipe: RecipeEntity,

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientEntity>,

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeId"
    )
    val steps: List<StepEntity>,

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "category",
        associateBy = Junction(RecipeCategoryCrossRef::class)
    )
    val categories: List<CategoryEntity>
)
