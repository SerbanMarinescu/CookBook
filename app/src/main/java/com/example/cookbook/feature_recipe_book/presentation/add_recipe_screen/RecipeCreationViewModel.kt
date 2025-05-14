package com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen

import androidx.lifecycle.ViewModel
import com.example.cookbook.feature_recipe_book.domain.model.Ingredient
import com.example.cookbook.feature_recipe_book.domain.model.Step
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.time.Duration

class RecipeCreationViewModel: ViewModel() {

    private val _state = MutableStateFlow(RecipeState())
    val state = _state.asStateFlow()

    fun onAction(action: CreateRecipeAction) {
        when(action) {
            CreateRecipeAction.GoNext -> {
                if(_state.value.currentStep < 5) {
                    _state.update {
                        it.copy(
                            currentStep = it.currentStep + 1
                        )
                    }
                }
            }
            CreateRecipeAction.GoPrevious -> {
                if(_state.value.currentStep > 1) {
                    _state.update {
                        it.copy(
                            currentStep = it.currentStep - 1
                        )
                    }
                }
            }

            is CreateRecipeAction.OnTitleChange -> {
                _state.update {
                    it.copy(
                        recipeTitle = action.title
                    )
                }
            }
            is CreateRecipeAction.OnDescriptionChange -> {
                _state.update {
                    it.copy(
                        recipeDescription = action.description
                    )
                }
            }
            is CreateRecipeAction.OnDurationChange -> {
                _state.update {
                    it.copy(
                        recipeDuration = action.duration ?: Duration.ZERO
                    )
                }
            }
            is CreateRecipeAction.OnServingsChange -> {
                _state.update {
                    it.copy(
                        servings = action.servings ?: 0
                    )
                }
            }

            is CreateRecipeAction.OnSelectedImage -> {
                _state.update {
                    it.copy(
                        imageUri = action.uri
                    )
                }
            }

            is CreateRecipeAction.OnIngredientNameChange -> {
                _state.update {
                    it.copy(
                        ingredients = it.ingredients.mapIndexed { index, ingredient ->
                            if(action.index == index) {
                                ingredient.copy(
                                    name = action.name,
                                    quantity = ingredient.quantity)
                            } else {
                                ingredient
                            }
                        }
                    )
                }
            }
            is CreateRecipeAction.OnIngredientQuantityChange -> {
                _state.update {
                    it.copy(
                        ingredients = it.ingredients.mapIndexed { index, ingredient ->
                            if(action.index == index) {
                                ingredient.copy(
                                    name = ingredient.name,
                                    quantity = action.quantity
                                )
                            } else {
                                ingredient
                            }
                        }
                    )
                }
            }

            is CreateRecipeAction.OnRemoveIngredient -> {
                _state.update {
                    it.copy(
                        ingredients = it.ingredients.filterIndexed { index, _ ->
                            action.index != index
                        }
                    )
                }
            }

            CreateRecipeAction.OnAddIngredient -> {
                _state.update {
                    it.copy(
                        ingredients = it.ingredients + Ingredient("", "")
                    )
                }
            }

            CreateRecipeAction.OnAddStep -> {
                _state.update {
                    it.copy(
                        steps = it.steps + Step(
                            number = it.steps.size + 1,
                            instruction = ""
                        )
                    )
                }
            }
            is CreateRecipeAction.OnStepChange -> {
                _state.update {
                    it.copy(
                        steps = it.steps.mapIndexed { index, step ->
                            if(action.index == index) {
                                step.copy(
                                    instruction = action.instruction
                                )
                            } else {
                                step
                            }
                        }
                    )
                }
            }
            is CreateRecipeAction.OnStepRemove -> {
                _state.update {
                    it.copy(
                        steps = it.steps.filterIndexed { index, _ ->
                            action.index != index
                        }.mapIndexed { index, step ->
                            step.copy(
                                number = index + 1
                            )
                        }
                    )
                }
            }
        }
    }

    private fun clearFields() {
        _state.update {
            RecipeState()
        }
    }
}