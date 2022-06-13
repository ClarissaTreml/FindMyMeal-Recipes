package com.example.findmymeal_recipes.screens.chosen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.findmymeal_recipes.models.Ingredients
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.models.getRecipes
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel
import com.example.findmymeal_recipes.viewmodels.RecipeViewModel
import com.example.findmymeal_recipes.viewmodels.ShoppingListViewModel
import com.example.findmymeal_recipes.widgets.RecipeCards

@Composable
fun ChosenScreen(navController: NavController,
                 viewModelChosen: ChoseIngredientsViewModel = viewModel(),
                 recipe: List<Recipe> = getRecipes(),
                 viewModelRecipe: RecipeViewModel = viewModel(),
                 viewModelShopping: ShoppingListViewModel = viewModel()
) {

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Header) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier
                        .padding(0.dp, 12.dp)
                        .clickable { navController.popBackStack() })

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

                Spacer(modifier = Modifier.width(20.dp))

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
                Content(
                    ingredientsList = viewModelChosen.chosenIngredients,
                    recipe = viewModelRecipe.recipes,
                    onItemClick = { recipeId -> navController.navigate(route = AppScreens.DetailScreen.name + "/$recipeId") },
                    //onAddClickShopping = {shoppingIngredient -> viewModelShopping.addShoppingIngredient(shoppingIngredient)}

                    //recipe = getRecipes()[0],
                )


            }
        }
    }

}

@Composable
fun Content(ingredientsList: List<Ingredients>,
            recipe: List<Recipe>,
            onItemClick: (String) -> Unit = {},
            //onAddClickShopping: (String) -> Unit = {}
) {
    Text(text = "Chosen Screen")

    LazyColumn() {
        items(items = ingredientsList) { ingredient ->
            for (i in recipe.indices) { //für Rezepte
                for (j in recipe[i].ingredients.indices) { //für Ingredients
                    // TODO show no duplicate recipes
                    if (recipe[i].ingredients[j] == ingredient.ingredient) {
                        Text(text = "same")
                        RecipeCards(recipe = recipe[i], onItemClick = onItemClick)
                    }
                    else{
                        //TODO in Deati
                        //onAddClickShopping(recipe[i].ingredients[j])

                    }
                }
            }
        }
    }


    Spacer(modifier = Modifier.height(20.dp))


}
