package com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen.components.RecipeImageCard
import com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen.components.RecipeIngredientsCard
import com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen.components.RecipeIntroCard
import com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen.components.RecipeStepCard
import com.example.cookbook.util.applyIf
import kotlin.time.Duration.Companion.minutes


@Composable
fun RecipeCreationScreen(
    state: RecipeState,
    onAction: (CreateRecipeAction) -> Unit
) {

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onAction(CreateRecipeAction.OnSelectedImage(it))
        }
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 8.dp,
        color = MaterialTheme.colorScheme.onSurface
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Step ${state.currentStep} of 5",
                fontSize = 20.sp
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                progress = {
                    state.currentStep / 5f
                }
            )

            Spacer(Modifier.height(20.dp))

            when (state.currentStep) {
                1 -> {
                    RecipeIntroCard(
                        label1 = "Title",
                        text1 = state.recipeTitle,
                        label2 = "Description",
                        text2 = state.recipeDescription,
                        onText1Change = {
                            onAction(CreateRecipeAction.OnTitleChange(it))
                        },
                        onText2Change = {
                            onAction(CreateRecipeAction.OnDescriptionChange(it))
                        },
                        isInputNumber = false
                    )
                }

                2 -> {
                    RecipeIntroCard(
                        label1 = "Servings",
                        text1 = state.servings.toString(),
                        label2 = "Duration (minutes)",
                        text2 = state.recipeDuration.inWholeMinutes.toString(),
                        onText1Change = { nrOfServings ->
                            onAction(CreateRecipeAction.OnServingsChange(nrOfServings.toIntOrNull()))
                        },
                        onText2Change = { duration ->
                            onAction(CreateRecipeAction.OnDurationChange(duration.toLongOrNull()?.minutes))
                        },
                        isInputNumber = true
                    )
                }

                3 -> {
                    RecipeImageCard(
                        imageUri = state.imageUri,
                        onImageClick = {
                            photoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    )
                }

                4 -> {
                    Column {
                        Text(
                            text = "Add Ingredients",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .applyIf(
                                    condition = state.ingredients.isNotEmpty(),
                                    modifier = Modifier.aspectRatio(16 / 9f)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            itemsIndexed(state.ingredients) { index, ingredient ->
                                RecipeIngredientsCard(
                                    name = ingredient.name,
                                    quantity = ingredient.quantity,
                                    onNameChange = {
                                        onAction(
                                            CreateRecipeAction.OnIngredientNameChange(
                                                it,
                                                index
                                            )
                                        )
                                    },
                                    onQuantityChange = {
                                        onAction(
                                            CreateRecipeAction.OnIngredientQuantityChange(
                                                it,
                                                index
                                            )
                                        )
                                    },
                                    onRemove = {
                                        onAction(CreateRecipeAction.OnRemoveIngredient(index))
                                    }
                                )
                            }
                            item {
                                Spacer(Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(Color.Red)
                                        .clickable {
                                            onAction(CreateRecipeAction.OnAddIngredient)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }

                5 -> {
                    Column {
                        Text(
                            text = "Add Instructions",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .applyIf(
                                    condition = state.steps.isNotEmpty(),
                                    modifier = Modifier.aspectRatio(16 / 9f)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            itemsIndexed(state.steps) { index, step ->
                                RecipeStepCard(
                                    step = step.number,
                                    instructions = step.instruction,
                                    onInstructionsChange = {
                                        onAction(CreateRecipeAction.OnStepChange(index, it))
                                    },
                                    onRemove = {
                                        onAction(CreateRecipeAction.OnStepRemove(index))
                                    }
                                )
                            }
                            item {
                                Spacer(Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(Color.Red)
                                        .clickable {
                                            onAction(CreateRecipeAction.OnAddStep)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.currentStep > 1) {
                    Button(
                        onClick = {
                            onAction(CreateRecipeAction.GoPrevious)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Previous"
                        )
                    }
                    Spacer(Modifier.width(15.dp))
                }

                if (state.currentStep == 5) {
                    Button(
                        onClick = {

                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text(
                            text = "Submit"
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            onAction(CreateRecipeAction.GoNext)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Next"
                        )
                    }
                }

            }
        }
    }
}