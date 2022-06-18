package com.example.findmymeal_recipes.screens.shoppinglist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.findmymeal_recipes.ui.theme.BgColor
import com.example.findmymeal_recipes.viewmodels.ShoppingListViewModel
import com.example.findmymeal_recipes.widgets.TopAppBarWidget

@Composable
fun ShoppingListScreen(
    navController: NavController,
    viewModelShopping: ShoppingListViewModel = viewModel()
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
                    text = "Shopping List",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )

                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Shopping Cart",
                    modifier = Modifier.size(40.dp)
                )

                Content(
                    shoppingIngredients = viewModelShopping.shoppingIngredients,
                    onCheckIngredient = { ingredient ->
                        viewModelShopping.removeShoppingIngredient(
                            ingredient
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Content(
    shoppingIngredients: List<String>,
    onCheckIngredient: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(90.dp, 20.dp, 0.dp, 0.dp),
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy((-20).dp)) {
            items(items = shoppingIngredients) { ingredient ->
                IconButton(modifier = Modifier.fillMaxWidth(),
                    onClick = { onCheckIngredient(ingredient) }) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Check Ingredient")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = ingredient)
                    }
                }
            }
        }
    }
}