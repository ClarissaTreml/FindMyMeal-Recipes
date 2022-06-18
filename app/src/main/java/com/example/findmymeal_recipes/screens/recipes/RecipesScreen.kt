package com.example.findmymeal_recipes.screens.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.*
import com.example.findmymeal_recipes.viewmodels.FavoritesViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.widgets.FilterRecipe
import com.example.findmymeal_recipes.widgets.RecipeCards
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun RecipesScreen(
    navController: NavController,
    viewModel: RecipeViewModel = viewModel(),
    viewModelFavorites: FavoritesViewModel = viewModel()

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
                    text = "Your Recipes",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )

                FilterRecipe(onScreenClick = {
                    navController.navigate(route = AppScreens.RecipesScreen.name)
                })

                Content(
                    recipe = viewModel.recipes,
                    onItemClick = { recipeId -> navController.navigate(route = AppScreens.DetailScreen.name + "/$recipeId") },
                    onDeleteClickRecipe = { recipe -> viewModel.removeRecipe(recipe) },
                    onAddRecipeToFavorite = {recipe -> viewModelFavorites.addFavorite(recipe)},
                    onDeleteOfFavorites = {recipe -> viewModelFavorites.removeFavorite(recipe)},
                    favorite = { recipe -> viewModelFavorites.isFavorite(recipe = recipe) },
                    favoriteIcon = true
                )
            }
        }
    }
}

@Composable
fun Content(
    recipe: List<Recipe>,
    onItemClick: (String) -> Unit = {},
    onDeleteClickRecipe: (Recipe) -> Unit = {},
    onAddRecipeToFavorite: (Recipe) -> Unit = {},
    onDeleteOfFavorites: (Recipe) -> Unit = {},
    //favorite: Boolean = false,
    favorite: @Composable (Recipe) -> Boolean = { false },
    favoriteIcon: Boolean,
    ) {
    //FilterRecipe(recipe = recipe[0])


    LazyColumn {
        items(items = recipe) { recipe ->
            RecipeCards(
                recipe = recipe,
                onItemClick = onItemClick,
                onDeleteClickRecipe = onDeleteClickRecipe,
                onAddRecipeToFavorite = {onAddRecipeToFavorite(recipe)},
                onDeleteOfFavorites = {onDeleteOfFavorites(recipe)},
                favorite = favorite(recipe),
                favoriteIcon = favoriteIcon
            )
        }
    }
}