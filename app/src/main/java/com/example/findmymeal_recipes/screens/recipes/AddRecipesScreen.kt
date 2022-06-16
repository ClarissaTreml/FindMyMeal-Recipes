package com.example.findmymeal_recipes.screens.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.widgets.AddRecipe
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun AddRecipesScreen(navController: NavController,
                     viewModel: RecipeViewModel = viewModel(),
                     viewModelIngredients: ChoseIngredientsViewModel = viewModel(),
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
                    .height(400.dp)
                    .padding(20.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                Text(
                    text = "Add your Recipes",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                AddRecipe(onAddClickIngredient = { ingredient -> viewModel.addIngredientsRecipe(ingredient) },
                    ingredients = viewModel.ingredientsRecipe,
                    onSaveClickRecipe = { recipe -> viewModel.addRecipe(recipe) },
                    onDeleteIngredient = { ingredient -> viewModel.removeIngredientsRecipe(ingredient) },
                    onNavigateClick = {navController.navigate(route = AppScreens.RecipesScreen.name)}
                    )
            }
        }
    }
}

