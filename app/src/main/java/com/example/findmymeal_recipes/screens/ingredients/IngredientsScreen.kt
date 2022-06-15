package com.example.findmymeal_recipes.screens.ingredients

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.findmymeal_recipes.models.Ingredients
import com.example.findmymeal_recipes.models.getIngredients
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel

@Composable
fun IngredientsScreen(
    navController: NavController = rememberNavController(),
    viewModel: ChoseIngredientsViewModel = viewModel(),
) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Header) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center
            ) {
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
                    navController = navController,
                    ingredientList = getIngredients(),
                    onAddClick = { ingredient -> viewModel.addIngredient(ingredient) },
                    onDeleteClick = { ingredient -> viewModel.removeIngredient(ingredient) },
                    //isClicked = { ingredient -> viewModel.isClicked(ingredient) },
                    isColored = { ingredient -> viewModel.isClicked(ingredient) },
                )
            }
        }
    }
}

@Composable
fun Content(
    navController: NavController,
    ingredientList: List<Ingredients>,
    onAddClick: (Ingredients) -> Unit,
    onDeleteClick: (Ingredients) -> Unit,
    isColored: @Composable (Ingredients) -> Boolean = { false },
) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier.height(450.dp).width(250.dp)) {
            Text(text = "Ingredients", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(50.dp))
            LazyColumn() {
                items(ingredientList) { ingredient ->
                    Card(
                        modifier = Modifier
                            .width(100.dp)
                            .height(30.dp)
                            .padding(0.dp, 0.dp, 0.dp, 10.dp),
                        elevation = 5.dp,
                        backgroundColor = Color.White
                    ) {
                        Click(
                            isColored = isColored(ingredient),
                            onAddClick = { ingredient -> onAddClick(ingredient) },
                            onDeleteClick = { ingredient -> onDeleteClick(ingredient) },
                            ingredient = ingredient
                        )
                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = { navController.navigate(route = AppScreens.ChosenScreen.name) },
            colors = ButtonDefaults.buttonColors(Header),
        ) {
            Text(text = "Find Me")
        }
    }

}


@Composable
fun Click(
    isColored: Boolean = false,
    onAddClick: (Ingredients) -> Unit,
    onDeleteClick: (Ingredients) -> Unit,
    ingredient: Ingredients
) {

    if (isColored) {
        IconButton(
            onClick = { onDeleteClick(ingredient) },
            Modifier.background(Color.Green)
        ) {
            Text(text = ingredient.ingredient)
        }
    } else {
        IconButton(
            onClick = { onAddClick(ingredient) },
            //Modifier.background(Color.Blue)
        ) {
            Text(text = ingredient.ingredient)
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
}