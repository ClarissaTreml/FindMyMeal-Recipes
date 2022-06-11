package com.example.findmymeal_recipes.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.findmymeal_recipes.R
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.ui.theme.BackColor
import com.example.findmymeal_recipes.ui.theme.FrontColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCards(
    recipe: Recipe,
    onItemClick: (String) -> Unit = {},
) {

    var cardFace by remember {
        mutableStateOf(CardFace.Front)
    }

    FlipCard(
        cardFace = cardFace,
        onClick = { cardFace = cardFace.next },
        front = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(recipe.images)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = "Recipe Front Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        back = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackColor),
            ) {
                Row(horizontalArrangement = Arrangement.End) {
                    IconButton(
                        onClick = { onItemClick(recipe.id) }) {
                        Image(
                            painterResource(R.drawable.fork),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Fork"
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row() {
                        Text(
                            text = recipe.difficulty,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = recipe.category,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = recipe.duration,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = recipe.description,
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        },
    )
}

@Composable
fun DetailRecipeCard(recipe: Recipe) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.h6,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row() {
            Text(
                text = recipe.difficulty,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = recipe.category,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = recipe.duration,
                style = MaterialTheme.typography.subtitle1
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = recipe.description,
            style = MaterialTheme.typography.body1
        )

        LazyColumn {
            items(items = recipe.ingredients) { ingredient ->
                Text(
                    text = ingredient,
                    style = MaterialTheme.typography.body1
                )

            }
        }

        Text(
            text = recipe.steps,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun AddRecipe(
    //onAddClick: (Recipe) --> Unit = {}
){

    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var images by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf(mutableListOf<Recipe>()) }
    var steps by remember { mutableStateOf("") }
    //var recipe by remember { mutableStateListOf<Recipe>()}


    OutlinedTextField(value = id, onValueChange = {value -> id = value },
        label = { Text(text = "id")})
    OutlinedTextField(value = name, onValueChange = {value -> name = value },
        label = { Text(text = "name")})
    OutlinedTextField(value = images, onValueChange = {value -> images = value },
        label = { Text(text = "images")})
    OutlinedTextField(value = difficulty, onValueChange = {value -> difficulty = value },
        label = { Text(text = "difficulty")})
    OutlinedTextField(value = description, onValueChange = {value -> description = value },
        label = { Text(text = "description")})
    OutlinedTextField(value = duration, onValueChange = {value -> duration = value },
        label = { Text(text = "duration")})
    OutlinedTextField(value = category, onValueChange = {value -> category = value },
        label = { Text(text = "category")})
    /*OutlinedTextField(value = ingredients, onValueChange = {value -> ingredients = value },
        label = { Text(text = "ingredients")})*/
    OutlinedTextField(value = steps, onValueChange = {value -> steps = value },
        label = { Text(text = "steps")})



    Button( onClick = { /*TODO*/ }) {
        Text(text = "Add")

    }

}













