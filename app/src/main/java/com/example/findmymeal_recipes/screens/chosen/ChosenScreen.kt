package com.example.findmymeal_recipes.screens.chosen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.findmymeal_recipes.models.Ingredients
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.widgets.RecipeCards
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun ChosenScreen(
    navController: NavController,
    viewModelChosen: ChoseIngredientsViewModel = viewModel(),
    viewModelRecipe: RecipeViewModel = viewModel(),
) {

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
                    .height(200.dp)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                Text(
                    text = "Recipes",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Content(
                    ingredientsList = viewModelChosen.chosenIngredients,
                    recipe = viewModelRecipe.recipes,
                    onItemClick = { recipeId -> navController.navigate(route = AppScreens.DetailScreen.name + "/$recipeId") },
                    favoriteIcon = true,
                    favorite = false,
                )
            }
        }
    }
}

@Composable
fun Content(
    ingredientsList: List<Ingredients>,
    recipe: List<Recipe>,
    onItemClick: (String) -> Unit = {},
    favorite: Boolean,
    favoriteIcon: Boolean,
) {
    LazyColumn() {
        items(items = ingredientsList) { ingredient ->
            for (i in recipe.indices) { //für Rezepte
                for (j in recipe[i].ingredients.indices) { //für Ingredients
                    if (recipe[i].ingredients[j] == ingredient.ingredient) {
                        if (recipe[i] != recipe[j]) {
                            RecipeCards(
                                recipe = recipe[i],
                                onItemClick = onItemClick,
                                favorite = favorite,
                                favoriteIcon = favoriteIcon,
                            )
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}
