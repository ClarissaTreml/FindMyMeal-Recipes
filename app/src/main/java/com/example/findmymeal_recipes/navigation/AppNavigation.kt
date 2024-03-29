package com.example.findmymeal_recipes.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.findmymeal_recipes.screens.chosen.ChosenScreen
import com.example.findmymeal_recipes.screens.detail.DetailScreen
import com.example.findmymeal_recipes.screens.favorite.FavoriteScreen
import com.example.findmymeal_recipes.screens.home.HomeScreen
import com.example.findmymeal_recipes.screens.ingredients.IngredientsScreen
import com.example.findmymeal_recipes.screens.recipes.AddRecipesScreen
import com.example.findmymeal_recipes.screens.recipes.EditRecipesScreen
import com.example.findmymeal_recipes.screens.recipes.RecipesScreen
import com.example.findmymeal_recipes.screens.shoppinglist.ShoppingListScreen
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel
import com.example.findmymeal_recipes.viewmodels.FavoritesViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.viewmodels.ShoppingListViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {

    val ingredientsViewModel: ChoseIngredientsViewModel = viewModel()
    ingredientsViewModel.chosenIngredients

    val recipeViewModel: RecipeViewModel = viewModel()

    val favoritesViewModel: FavoritesViewModel = viewModel()

    val shoppingListViewModel: ShoppingListViewModel = viewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name) {
        composable(route = AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(
            route = AppScreens.DetailScreen.name + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                recipeId = backStackEntry.arguments?.getString("recipeId"),
                viewModelRecipe = recipeViewModel,
                viewModelShopping = shoppingListViewModel,
            )
        }

        composable(route = AppScreens.IngredientsScreen.name) {
            IngredientsScreen(navController = navController, viewModel = ingredientsViewModel)
        }

        composable(route = AppScreens.RecipesScreen.name) {
            RecipesScreen(
                navController = navController, viewModel = recipeViewModel,
                viewModelFavorites = favoritesViewModel
            )
        }

        composable(route = AppScreens.ShoppingListScreen.name) {
            ShoppingListScreen(
                navController = navController,
                viewModelShopping = shoppingListViewModel
            )
        }

        composable(route = AppScreens.ChosenScreen.name) {
            ChosenScreen(
                navController = navController, viewModelChosen = ingredientsViewModel,
                viewModelRecipe = recipeViewModel
            )
        }

        composable(route = AppScreens.FavoriteScreen.name) {
            FavoriteScreen(
                navController = navController, viewModelFavorite = favoritesViewModel,
                viewModelRecipes = recipeViewModel
            )
        }

        composable(route = AppScreens.AddRecipesScreen.name) {
            AddRecipesScreen(navController = navController, viewModel = recipeViewModel)
        }

        composable(
            route = AppScreens.EditRecipeScreen.name + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            EditRecipesScreen(
                navController = navController,
                recipeId = backStackEntry.arguments?.getString("recipeId"),
                viewModel = recipeViewModel,
            )
        }
    }
}
