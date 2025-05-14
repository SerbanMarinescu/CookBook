@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cookbook.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen.RecipeCreationScreen
import com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen.RecipeCreationViewModel
import com.example.cookbook.navigation.components.NotchedBottomBar

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: NavigationViewModel
) {
    Scaffold(
        bottomBar = {
            NotchedBottomBar(
                fabSize = 70.dp,
                menuOptions = viewModel.menuOptions,
                selectedIndex = viewModel.selectedIndex,
                itemSelected = {
                    viewModel.onSelectedItem(it)
                    when(it) {
                        0 -> navController.navigate(RecipesScreenRoute)
                        1 -> navController.navigate(ExploreRecipesScreenRoute)
                        2 -> navController.navigate(ShoppingScreenRoute)
                        3 -> navController.navigate(TemplateScreenRoute)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.toggleRecipeCreatingDialog()
                },
                shape = CircleShape,
                modifier = Modifier
                    .size(70.dp)
                    .offset(y = 45.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        val recipeCreationViewModel = viewModel<RecipeCreationViewModel>()
        val state by recipeCreationViewModel.state.collectAsStateWithLifecycle()

        if(viewModel.isCreatingRecipe) {
            BasicAlertDialog(
                onDismissRequest = {
                    viewModel.toggleRecipeCreatingDialog()
                }
            ) {
                RecipeCreationScreen(
                    state = state,
                    onAction = recipeCreationViewModel::onAction
                )
            }
        }

        NavHost(
            navController = navController,
            startDestination = RecipesScreenRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable<RecipesScreenRoute> {
                Text("My Recipes")
            }
            composable<ExploreRecipesScreenRoute> {
                Text("Explore")
            }
            composable<ShoppingScreenRoute> {
                Text("Shopping")
            }
            composable<TemplateScreenRoute> {
                Text("Template")
            }
        }
    }
}
