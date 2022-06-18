package com.example.findmymeal_recipes.screens.ingredients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.findmymeal_recipes.models.Ingredients
import com.example.findmymeal_recipes.models.getIngredients
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.ui.theme.BgColor2
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.ui.theme.four
import com.example.findmymeal_recipes.viewmodels.ChoseIngredientsViewModel
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun IngredientsScreen(
    navController: NavController = rememberNavController(),
    viewModel: ChoseIngredientsViewModel = viewModel(),
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
            )
            {
                Text(
                    text = "Choose your Ingredients",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

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
        Column(modifier = Modifier
            .height(450.dp)
            .width(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn() {
                items(ingredientList) { ingredient ->
                    Card(
                        modifier = Modifier
                            .width(170.dp)
                            .height(40.dp)
                            .padding(0.dp, 0.dp, 0.dp, 10.dp),
                        elevation = 5.dp,
                        backgroundColor = Color.White,

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
            Text(text = "Find Me",
                style = MaterialTheme.typography.h3
            )
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
            Modifier.background(four)
        ) {
            Text(text = ingredient.ingredient,
            style = MaterialTheme.typography.h2
           )
        }
    } else {
        IconButton(
            onClick = { onAddClick(ingredient) },
            //Modifier.background(Color.Blue)
        ) {
            Text(text = ingredient.ingredient,
                style = MaterialTheme.typography.h2
            )
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
}