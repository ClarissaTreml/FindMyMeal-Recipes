package com.example.findmymeal_recipes.screens.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.widgets.EditRecipe
import com.example.findmymeal_recipes.widgets.ViewIngredients

@Composable
fun EditRecipesScreen(navController: NavController,
                      viewModel: RecipeViewModel = viewModel(),
                      recipeId: String? = "0"
) {

    val oldRecipe = filterRecipe(recipeId = recipeId, recipes = viewModel.getAllRecipes())
    val oldIngredient = oldRecipe.ingredients

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Header) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(modifier = Modifier.width(80.dp),
                    onClick = { navController.navigate(route = AppScreens.IngredientsScreen.name) }) {
                    Text(text = "Ingredients", modifier = Modifier.fillMaxWidth())
                }
                Spacer(modifier = Modifier.width(20.dp))

                IconButton(modifier = Modifier.width(100.dp),
                    onClick = { navController.navigate(route = AppScreens.ShoppingListScreen.name) }) {
                    Text(text = "Shopping-List", modifier = Modifier.fillMaxWidth())
                }
                Spacer(modifier = Modifier.width(20.dp))

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(modifier = Modifier.width(100.dp),
                    onClick = {}) {
                    Text(text = "Add Recipe", modifier = Modifier.fillMaxWidth())
                }

                Icon(
                    modifier = Modifier
                        .padding(0.dp, 12.dp)
                        .clickable { navController.navigate(route = AppScreens.FavoriteScreen.name) },
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorites"
                )
            }

        }
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
                    text = "Find My Meal\nRecipes",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { navController.navigate(route = AppScreens.HomeScreen.name) }
                )

                Text(text = "Edit Screen")


                EditRecipe(
                    onAddClickIngredient = { ingredient ->
                        viewModel.addIngredientsRecipe(ingredient)
                    },
                    ingredients = viewModel.ingredientsRecipe,
                    onAddClickRecipe = { newRecipe -> viewModel.addRecipe(newRecipe) },
                    oldRecipe = oldRecipe,
                    onDeleteClickRecipe = { oldRecipe -> viewModel.removeRecipe(oldRecipe) },
                    oldIngredient = oldIngredient
                )
                viewModel.clearIngredientsList()
            }
        }
    }
}