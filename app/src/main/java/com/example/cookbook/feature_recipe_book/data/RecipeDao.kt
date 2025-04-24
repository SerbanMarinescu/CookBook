package com.example.cookbook.feature_recipe_book.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.cookbook.feature_recipe_book.data.entities.CategoryEntity
import com.example.cookbook.feature_recipe_book.data.entities.IngredientEntity
import com.example.cookbook.feature_recipe_book.data.entities.RecipeCategoryCrossRef
import com.example.cookbook.feature_recipe_book.data.entities.RecipeEntity
import com.example.cookbook.feature_recipe_book.data.entities.RecipeWithDetailsEntity
import com.example.cookbook.feature_recipe_book.data.entities.StepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipe(recipeEntity: RecipeEntity)

    @Upsert
    suspend fun upsertIngredients(ingredientEntity: List<IngredientEntity>)

    @Upsert
    suspend fun upsertSteps(stepEntity: List<StepEntity>)

    @Upsert
    suspend fun upsertCategories(categoryEntity: List<CategoryEntity>)

    @Upsert
    suspend fun upsertCategoryCrossRefs(crossRef: List<RecipeCategoryCrossRef>)

    @Transaction
    suspend fun insertRecipeWithDetails(
        recipe: RecipeEntity,
        ingredients: List<IngredientEntity>,
        steps: List<StepEntity>,
        categories: List<CategoryEntity>
    ) {
        upsertRecipe(recipe)
        upsertIngredients(ingredients)
        upsertSteps(steps)
        upsertCategories(categories)

        val categoryCrossRefList = categories.map {
            RecipeCategoryCrossRef(
                recipeId = recipe.recipeId,
                category = it.category
            )
        }

        upsertCategoryCrossRefs(categoryCrossRefList)
    }

    @Transaction
    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): Flow<List<RecipeWithDetailsEntity>>

    @Transaction
    @Query("SELECT * FROM Recipe WHERE recipeId = :recipeId")
    suspend fun getRecipeById(recipeId: String): RecipeWithDetailsEntity

    @Transaction
    @Query("DELETE FROM Recipe WHERE recipeId = :recipeId")
    suspend fun deleteRecipeById(recipeId: String)
}