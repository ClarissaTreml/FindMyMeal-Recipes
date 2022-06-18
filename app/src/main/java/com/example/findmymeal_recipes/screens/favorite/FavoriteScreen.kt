package com.example.findmymeal_recipes.screens.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.ui.theme.BgColor2
import com.example.findmymeal_recipes.viewmodels.FavoritesViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.widgets.RecipeCards
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun FavoriteScreen(navController: NavController = rememberNavController(),
                   viewModelFavorite: FavoritesViewModel = viewModel(),
                   viewModelRecipes: RecipeViewModel = viewModel()
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
                    text = "Your Favorites",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column() {
                    Content(
                        recipe = viewModelFavorite.favoriteRecipe,
                        onItemClick = { recipeId -> navController.navigate(route = AppScreens.DetailScreen.name + "/$recipeId") },
                        onDeleteClickRecipe = { recipe -> viewModelRecipes.removeRecipe(recipe)
                                              viewModelFavorite.removeFavorite(recipe)},
                        onAddRecipeToFavorite = {recipe -> viewModelFavorite.addFavorite(recipe)},
                        onDeleteOfFavorites = {recipe -> viewModelFavorite.removeFavorite(recipe)},
                        favorite = { recipe -> viewModelFavorite.isFavorite(recipe = recipe) },
                        favoriteIcon = true
                    )
                }
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
    favoriteIcon: Boolean
) {
    //FilterRecipe(recipe = recipe[0])

    LazyColumn {
        items(items = recipe) { recipe ->
            RecipeCards(
                recipe = recipe,
                onItemClick = onItemClick,
                onDeleteClickRecipe = onDeleteClickRecipe,
                onAddRecipeToFavorite = onAddRecipeToFavorite,
                onDeleteOfFavorites = onDeleteOfFavorites,
                favorite = favorite(recipe),
                favoriteIcon = favoriteIcon
            )
        }
    }
}