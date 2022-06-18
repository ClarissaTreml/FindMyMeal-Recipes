package com.example.findmymeal_recipes.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.*
import com.example.findmymeal_recipes.widgets.init

@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {

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
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Find My Meal\nRecipes",
                style = MaterialTheme.typography.h1, textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(0.dp, 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                IconButton(modifier = Modifier
                    .fillMaxWidth()
                    .background(Header),
                    onClick = { navController.navigate(route = AppScreens.IngredientsScreen.name) }) {
                    Text(
                        text = "Choose Ingredients",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                IconButton(modifier = Modifier
                    .fillMaxWidth()
                    .background(Header),
                    onClick = {
                        init = "All"
                        navController.navigate(route = AppScreens.RecipesScreen.name) }) {
                    Text(
                        text = "My Recipes",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                IconButton(modifier = Modifier
                    .fillMaxWidth()
                    .background(Header),
                    onClick = {navController.navigate(route = AppScreens.AddRecipesScreen.name)}) {
                    Text(
                        text = "Add Recipe",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                IconButton(modifier = Modifier
                    .fillMaxWidth()
                    .background(Header),
                    onClick = {navController.navigate(route = AppScreens.FavoriteScreen.name)}) {
                    Text(
                        text = "My Favorites",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))


                IconButton(modifier = Modifier
                    .fillMaxWidth()
                    .background(Header),
                    onClick = {
                        navController.navigate(route = AppScreens.ShoppingListScreen.name) }) {
                    Text(
                        text = "Shopping List",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5
                    )
                }

            }
        }
    }
}

