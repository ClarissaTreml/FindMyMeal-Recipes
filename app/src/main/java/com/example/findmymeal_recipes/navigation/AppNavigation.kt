package com.example.findmymeal_recipes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.findmymeal_recipes.db.RecipesDB
import com.example.findmymeal_recipes.repositories.RecipeRepository
import com.example.findmymeal_recipes.screens.chosen.ChosenScreen
import com.example.findmymeal_recipes.screens.detail.DetailScreen
import com.example.findmymeal_recipes.screens.favorite.FavoriteScreen
import com.example.findmymeal_recipes.screens.home.HomeScreen
import com.example.findmymeal_recipes.screens.ingredients.IngredientsScreen
import com.example.findmymeal_recipes.screens.recipes.RecipesScreen
import com.example.findmymeal_recipes.screens.shoppinglist.ShoppingListScreen
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModelFactory

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    val ingredientsViewModel: ChoseIngredientsViewModel = viewModel()
    ingredientsViewModel.chosenIngredients

    // Pattern um ViewModel zu instanzieren:
    val context = LocalContext.current      // context aus Activity herholen. Wichtig uu wissen in was für einen Context sich die Actiit befindet
    val db = RecipesDB.getDatabase(context = context)        // db Instance von Datenbank,
    val repository = RecipeRepository(dao = db.recipeDao())  // Repos braucht ein DAO, was wir von der Datenbank holen

    val recipeViewModel: RecipeViewModel = viewModel(
        factory = RecipeViewModelFactory(repository = repository)       // Erklärt wie ViewModel erstellt wird.
    )
    //recipeViewModel.
    // TODO: obere Zeile ergänzen

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name) {
        composable(route = AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(route = AppScreens.DetailScreen.name){
            DetailScreen(navController = navController, viewModel = recipeViewModel)}

        composable(route = AppScreens.IngredientsScreen.name) {
            IngredientsScreen(navController = navController, viewModel = ingredientsViewModel)
        }

        composable(route = AppScreens.RecipesScreen.name) {
            RecipesScreen(navController = navController, viewModel = recipeViewModel)
        }

        composable(route = AppScreens.ShoppingListScreen.name) {
            ShoppingListScreen(navController = navController)
        }

        composable(route = AppScreens.ChosenScreen.name) {
            ChosenScreen(navController = navController, viewModelC = ingredientsViewModel, viewModelR = recipeViewModel)
        }

        composable(route = AppScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
    }

}