package com.example.findmymeal_recipes.screens.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.screens.detail.filterRecipe
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.widgets.EditRecipe
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun EditRecipesScreen(
    navController: NavController,
    viewModel: RecipeViewModel = viewModel(),
    recipeId: String? = "0",
) {

    val oldRecipe = filterRecipe(recipeId = recipeId, recipes = viewModel.getAllRecipes())
    val oldIngredient = oldRecipe.ingredients

    Scaffold(topBar = {
        TopAppBarWidget(navController = navController)
    }) {
        Surface(
            color = BgColor, modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                Text(
                    text = "Edit your Recipes",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                EditRecipe(
                    onAddClickIngredient = { ingredient -> viewModel.addIngredientsRecipe(ingredient) },
                    ingredients = viewModel.ingredientsRecipe,
                    onAddClickRecipe = { newRecipe -> viewModel.addRecipe(newRecipe) },
                    oldRecipe = oldRecipe,
                    onDeleteClickRecipe = { oldRecipe -> viewModel.removeRecipe(oldRecipe) },
                    oldIngredient = oldIngredient,
                    onDeleteIngredient = { ingredient ->
                        viewModel.removeIngredientsRecipe(
                            ingredient
                        )
                    },
                    onNavigateClick = { navController.navigate(route = AppScreens.RecipesScreen.name) }
                )
                viewModel.clearIngredientsList()
            }
        }
    }
}