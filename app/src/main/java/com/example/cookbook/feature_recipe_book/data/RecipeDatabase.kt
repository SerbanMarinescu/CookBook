package com.example.cookbook.feature_recipe_book.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cookbook.feature_recipe_book.data.entities.CategoryEntity
import com.example.cookbook.feature_recipe_book.data.entities.IngredientEntity
import com.example.cookbook.feature_recipe_book.data.entities.RecipeCategoryCrossRef
import com.example.cookbook.feature_recipe_book.data.entities.RecipeEntity
import com.example.cookbook.feature_recipe_book.data.entities.StepEntity
import com.example.cookbook.feature_recipe_book.data.util.Converters

@Database(
    entities = [
        RecipeEntity::class,
        IngredientEntity::class,
        StepEntity::class,
        CategoryEntity::class,
        RecipeCategoryCrossRef::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
}