package com.example.findmymeal_recipes.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.ui.theme.BackColor
import com.example.findmymeal_recipes.ui.theme.FrontColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCards(
    recipe: Recipe
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
                    .fillMaxSize()
                    .background(FrontColor), //recipe image - blurred
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
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.h3,
                )
            }
        },
    )

}