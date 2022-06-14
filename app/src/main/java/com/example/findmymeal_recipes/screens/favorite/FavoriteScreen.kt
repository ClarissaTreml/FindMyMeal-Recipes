package com.example.findmymeal_recipes.screens.favorite

import androidx.compose.foundation.clickable
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
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.viewmodels.FavoritesViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.widgets.FavoriteCard
import com.example.findmymeal_recipes.widgets.RecipeCards

@Composable
fun FavoriteScreen(navController: NavController = rememberNavController(),
                   viewModelFavorite: FavoritesViewModel = viewModel(),
                   viewModelRecipes: RecipeViewModel = viewModel()
) {
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

                IconButton(modifier = Modifier.width(60.dp),
                    onClick = { navController.navigate(route = AppScreens.RecipesScreen.name) }) {
                    Text(text = "Recipes", modifier = Modifier.fillMaxWidth())
                }

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(modifier = Modifier.width(100.dp),
                    onClick = { navController.navigate(route = AppScreens.ShoppingListScreen.name) }) {
                    Text(text = "Shopping-List", modifier = Modifier.fillMaxWidth())
                }
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
                    .height(200.dp)
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
                Column() {
                    Text(text = "Favorite Screen")
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