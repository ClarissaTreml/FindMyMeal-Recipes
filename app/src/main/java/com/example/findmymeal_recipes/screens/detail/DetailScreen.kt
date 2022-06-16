package com.example.findmymeal_recipes.screens.detail

import android.util.Log
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
import com.example.findmymeal_recipes.models.Ingredients
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.viewmodels.ShoppingListViewModel
import com.example.findmymeal_recipes.widgets.DetailRecipeCard
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun DetailScreen(
    navController: NavController,
    recipeId: String? = "0",
    viewModelRecipe: RecipeViewModel = viewModel(),
    viewModelShopping: ShoppingListViewModel = viewModel(),
) {

    val recipe = filterRecipe(recipeId = recipeId, recipes = viewModelRecipe.getAllRecipes())

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
                    text = "Recipe Details",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                Content(recipe = recipe, onEditClick = { recipeId ->
                    navController.navigate(route = AppScreens.EditRecipeScreen.name + "/$recipeId")
                },
                    shoppingIngredient = viewModelShopping.shoppingIngredients,
                    onAddToShoppingList = { recipe ->
                        viewModelShopping.addShoppingIngredient(recipe)
                    })

            }
        }
    }
}

@Composable
fun Content(
    recipe: Recipe, onEditClick: (String) -> Unit = {},
    shoppingIngredient: List<String>,
    onAddToShoppingList: (String) -> Unit = {}
) {
    Column {
        DetailRecipeCard(
            recipe = recipe, onEditClick = onEditClick, shoppingIngredient = shoppingIngredient,
            onAddToShoppingList = onAddToShoppingList
        )
    }

    /*Button(onClick = {
        addToShoppingList(
            recipe = recipe,
            shoppingIngredient = shoppingIngredient,
            onAddToShoppingList = onAddToShoppingList
        )
    }) {
        Text(text = "Add Ingredients To Shopping List")
    }*/
}

fun filterRecipe(recipeId: String?, recipes: List<Recipe>): Recipe {
    return recipes.filter { recipe -> recipe.id == recipeId }[0]
}


fun addToShoppingList(
    recipe: Recipe,
    shoppingIngredient: List<String>, onAddToShoppingList: (String) -> Unit = {}
) {
    for (i in recipe.ingredients.indices) {
        if (!shoppingIngredient.contains(recipe.ingredients[i]))
            onAddToShoppingList(recipe.ingredients[i])
    }

}
