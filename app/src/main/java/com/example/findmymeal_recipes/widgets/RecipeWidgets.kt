package com.example.findmymeal_recipes.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
                            .data(recipe.images[0])
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
                        onClick = { onItemClick(recipe.id)}) {
                        Image(
                            painterResource(R.drawable.fork),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Fork")
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
fun DetailRecipeCard(recipe: Recipe){
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

        LazyColumn{
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