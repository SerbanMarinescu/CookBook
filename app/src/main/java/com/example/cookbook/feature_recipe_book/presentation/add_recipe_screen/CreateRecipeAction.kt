package com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen

import android.net.Uri
import kotlin.time.Duration

sealed interface CreateRecipeAction {
    data object GoNext: CreateRecipeAction
    data object GoPrevious: CreateRecipeAction
    data class OnTitleChange(val title: String): CreateRecipeAction
    data class OnDescriptionChange(val description: String): CreateRecipeAction
    data class OnServingsChange(val servings: Int?): CreateRecipeAction
    data class OnDurationChange(val duration: Duration?): CreateRecipeAction
    data class OnSelectedImage(val uri: Uri?): CreateRecipeAction
    data class OnIngredientNameChange(val name: String, val index: Int): CreateRecipeAction
    data class OnIngredientQuantityChange(val quantity: String, val index: Int): CreateRecipeAction
    data class OnRemoveIngredient(val index: Int): CreateRecipeAction
    data object OnAddIngredient: CreateRecipeAction
    data class OnStepChange(val index: Int, val instruction: String): CreateRecipeAction
    data class OnStepRemove(val index: Int): CreateRecipeAction
    data object OnAddStep: CreateRecipeAction
}