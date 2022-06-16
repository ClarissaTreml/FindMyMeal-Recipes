package com.example.findmymeal_recipes.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.findmymeal_recipes.R
import com.example.findmymeal_recipes.models.Ingredients
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.screens.detail.addToShoppingList
import com.example.findmymeal_recipes.ui.theme.BackColor
import com.example.findmymeal_recipes.ui.theme.Header

var init: String? = "All"
var difficulty: String? = null
var category: String? = null

@Composable
fun FilterRecipe(
    onScreenClick: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var difficultyExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }


    Box() {
        IconButton(modifier = Modifier
            .width(120.dp),onClick = { expanded = true }) {
            Row(modifier = Modifier
                .width(100.dp)) {
                Text(text = "Filter")
                Icon(Icons.Default.MoreVert, contentDescription = "Filter")
            }


        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            IconButton(modifier = Modifier
                .width(120.dp),onClick = { difficultyExpanded = true }) {
                Row() {
                    Text(text = "by Difficulty")
                    Icon(Icons.Default.MoreVert, contentDescription = "Filter by Difficulty")
                }


            }
            Divider()
            DropdownMenu(
                expanded = difficultyExpanded,
                onDismissRequest = { difficultyExpanded = false }) {

                DropdownMenuItem(onClick = {
                    difficulty = "Beginner"
                    init = null
                    category = null
                    onScreenClick("")
                }) {
                    Text(text = "Beginner")

                }
                Divider()
                DropdownMenuItem(onClick = {
                    difficulty = "Advanced"
                    init = null
                    category = null
                    onScreenClick("")
                }) {
                    Text(text = "Advanced")
                }
                Divider()
                DropdownMenuItem(onClick = {
                    difficulty = "Pro"
                    init = null
                    category = null
                    onScreenClick("")
                }) {
                    Text(text = "Pro")
                }
            }
            IconButton(modifier = Modifier
                .width(120.dp),onClick = { categoryExpanded = true }) {
                Row() {
                    Text(text = "by Category")
                    Icon(Icons.Default.MoreVert, contentDescription = "Filter by Category")
                }


            }
            DropdownMenu(
                expanded = categoryExpanded,
                onDismissRequest = { categoryExpanded = false }) {
                DropdownMenuItem(onClick = {
                    category = "Breakfast"
                    init = null
                    difficulty = null
                    onScreenClick("")
                }) {
                    Text(text = "Breakfast")
                }
                Divider()
                DropdownMenuItem(onClick = {
                    category = "Lunch"
                    init = null
                    difficulty = null
                    onScreenClick("")
                }) {
                    Text(text = "Lunch")
                }
                Divider()
                DropdownMenuItem(onClick = {
                    category = "Dinner"
                    init = null
                    difficulty = null
                    onScreenClick("")
                }) {
                    Text(text = "Dinner")
                }
                Divider()
                DropdownMenuItem(onClick = {
                    category = "Dessert"
                    init = null
                    difficulty = null
                    onScreenClick("")
                }) {
                    Text(text = "Dessert")
                }
            }

        }
    }
}

@Composable
fun RecipeCards(
    recipe: Recipe,
    onItemClick: (String) -> Unit = {},
    onDeleteClickRecipe: (Recipe) -> Unit = {},
    onAddRecipeToFavorite: (Recipe) -> Unit = {},
    onDeleteOfFavorites: (Recipe) -> Unit = {},
    favorite: Boolean,
    favoriteIcon: Boolean
) {
    if (recipe.difficulty.lowercase() == (difficulty?.lowercase() ?: difficulty)) {
        RecipeCards2(
            recipe = recipe,
            onItemClick = onItemClick,
            onDeleteClickRecipe = onDeleteClickRecipe,
            onAddRecipeToFavorite = onAddRecipeToFavorite,
            onDeleteOfFavorites = onDeleteOfFavorites,
            favoriteIcon = favoriteIcon,
            favorite = favorite
        ) //, favorite = favorite, favoriteIcon = favoriteIcon)
    } else if (init == "All") {
        RecipeCards2(
            recipe = recipe,
            onItemClick = onItemClick,
            onDeleteClickRecipe = onDeleteClickRecipe,
            onAddRecipeToFavorite = onAddRecipeToFavorite,
            onDeleteOfFavorites = onDeleteOfFavorites,
            favoriteIcon = favoriteIcon,
            favorite = favorite
        )//, favorite = favorite, favoriteIcon = favoriteIcon)
    } else if (recipe.category.lowercase() == (category?.lowercase() ?: category)) {
        RecipeCards2(
            recipe = recipe,
            onItemClick = onItemClick,
            onDeleteClickRecipe = onDeleteClickRecipe,
            onAddRecipeToFavorite = onAddRecipeToFavorite,
            onDeleteOfFavorites = onDeleteOfFavorites,
            favoriteIcon = favoriteIcon,
            favorite = favorite
        )//, favorite = favorite, favoriteIcon = favoriteIcon)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCards2(
    recipe: Recipe,
    onItemClick: (String) -> Unit = {},
    onDeleteClickRecipe: (Recipe) -> Unit = {},
    onAddRecipeToFavorite: (Recipe) -> Unit = {},
    onDeleteOfFavorites: (Recipe) -> Unit = {},
    favorite: Boolean,
    favoriteIcon: Boolean
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
                    IconButton(
                        onClick = { onDeleteClickRecipe(recipe) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Button"
                        )
                    }
                    if (favoriteIcon) {
                        FavoriteIcon(
                            recipe = recipe,
                            onAddRecipeToFavorite = { recipe -> onAddRecipeToFavorite(recipe) },
                            onDeleteOfFavorites = { recipe -> onDeleteOfFavorites(recipe) },
                            favorite = favorite
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
fun FavoriteIcon(
    recipe: Recipe,
    onAddRecipeToFavorite: (Recipe) -> Unit,
    onDeleteOfFavorites: (Recipe) -> Unit,
    favorite: Boolean = false,
) {
    if (favorite) {
        IconButton(onClick = { onDeleteOfFavorites(recipe) }) {
            Icon(Icons.Default.Favorite, contentDescription = "FavoriteClicked", tint = Color.Cyan)
        }
    } else {
        IconButton(onClick = { onAddRecipeToFavorite(recipe) }) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "FavoriteNotClicked",
                tint = Color.Cyan
            )
        }
    }
}

@Composable
fun DetailRecipeCard(
    recipe: Recipe, onEditClick: (String) -> Unit = {},
    shoppingIngredient: List<String>,
    onAddToShoppingList: (String) -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        IconButton(
            onClick = { onEditClick(recipe.id) },
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Button"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {



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

            Text(
                text = recipe.steps,
                style = MaterialTheme.typography.body1
            )
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),onClick = {
                addToShoppingList(
                    recipe = recipe,
                    shoppingIngredient = shoppingIngredient,
                    onAddToShoppingList = onAddToShoppingList
                )

            }, interactionSource = interactionSource) {
                Text(if (isPressed) "Added to Shopping List" else "Add Ingredients To Shopping List")
            }
        }
    }


}

@Composable
fun ViewIngredients(
    ingredients: List<String> = listOf(),
    onDeleteIngredient: (String) -> Unit = {},
) {
    Column(modifier = Modifier
        .height(120.dp)
        .width(200.dp)) {
        LazyColumn {
            items(ingredients) { ingredient ->
                IconButton(onClick = { onDeleteIngredient(ingredient) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Ingredient")
                    Text(text = ingredient)
                }
            }
        }
    }
}


@Composable
fun AddRecipe(
    onAddClickIngredient: (String) -> Unit = {},
    ingredients: List<String> = listOf(),
    onSaveClickRecipe: (Recipe) -> Unit = {},
    onDeleteIngredient: (String) -> Unit = {},
    onNavigateClick: (String) -> Unit = {},

    ) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        var id by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var images by remember { mutableStateOf("") }
        var difficulty by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var duration by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var ingredient by remember { mutableStateOf("") }
        var steps by remember { mutableStateOf("") }

        OutlinedTextField(value = id, onValueChange = { value -> id = value },
            label = { Text(text = "id") })
        OutlinedTextField(value = name, onValueChange = { value -> name = value },
            label = { Text(text = "name") })
        OutlinedTextField(value = images, onValueChange = { value -> images = value },
            label = { Text(text = "images") })
        OutlinedTextField(value = difficulty, onValueChange = { value -> difficulty = value },
            label = { Text(text = "difficulty") })
        OutlinedTextField(value = description, onValueChange = { value -> description = value },
            label = { Text(text = "description") })
        OutlinedTextField(value = duration, onValueChange = { value -> duration = value },
            label = { Text(text = "duration") })
        OutlinedTextField(value = category, onValueChange = { value -> category = value },
            label = { Text(text = "category") })

        OutlinedTextField(
            value = ingredient,
            onValueChange = { value ->
                ingredient = value.lowercase()
            },
            leadingIcon = {
                IconButton(onClick = {
                    onAddClickIngredient(ingredient)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "addIngredient")
                }
            },

            label = { Text(text = "Ingredient") },
            placeholder = { Text(text = "Enter your ingredient") },
        )

        ViewIngredients(
            ingredients = ingredients,
            onDeleteIngredient = onDeleteIngredient
        )

        OutlinedTextField(value = steps, onValueChange = { value -> steps = value },
            label = { Text(text = "steps") })

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                if (id.isNotEmpty() && name.isNotEmpty() && images.isNotEmpty()
                    && difficulty.isNotEmpty() && description.isNotEmpty() &&
                    duration.isNotEmpty() && category.isNotEmpty() &&
                    ingredient.isNotEmpty() && steps.isNotEmpty()
                ) {
                    val newRecipe = Recipe(
                        id, name, images, difficulty,
                        description, duration, category, ingredients, steps
                    )

                    onSaveClickRecipe(newRecipe)
                    onNavigateClick("")

                }

            }) {

            Text(text = "Add")
        }
    }

}

@Composable
fun EditRecipe(
    onAddClickIngredient: (String) -> Unit = {},
    ingredients: List<String> = listOf(),
    onAddClickRecipe: (Recipe) -> Unit = {},
    oldRecipe: Recipe,
    onDeleteClickRecipe: (Recipe) -> Unit = {},
    oldIngredient: List<String>,
    onDeleteIngredient: (String) -> Unit = {},
    onNavigateClick: (String) -> Unit = {},
) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        //var id by remember { mutableStateOf("") }
        var name by remember { mutableStateOf(oldRecipe.name) }
        var images by remember { mutableStateOf(oldRecipe.images) }
        var difficulty by remember { mutableStateOf(oldRecipe.difficulty) }
        var description by remember { mutableStateOf(oldRecipe.description) }
        var duration by remember { mutableStateOf(oldRecipe.duration) }
        var category by remember { mutableStateOf(oldRecipe.category) }
        var ingredient by remember { mutableStateOf("") }
        var steps by remember { mutableStateOf(oldRecipe.steps) }

        /*OutlinedTextField(value = id, onValueChange = { id = oldRecipe.id },
            label = { Text(text = "id") })*/
        OutlinedTextField(value = name, onValueChange = { name = it },
            label = { Text(text = "name") })
        OutlinedTextField(value = images, onValueChange = { images = it },
            label = { Text(text = "images") })
        OutlinedTextField(value = difficulty, onValueChange = { difficulty = it },
            label = { Text(text = "difficulty") })
        OutlinedTextField(value = description, onValueChange = { description = it },
            label = { Text(text = "description") })
        OutlinedTextField(value = duration, onValueChange = { duration = it },
            label = { Text(text = "duration") })
        OutlinedTextField(value = category, onValueChange = { category = it },
            label = { Text(text = "category") })

        OutlinedTextField(
            value = ingredient.lowercase(),
            onValueChange = { value ->
                ingredient = value.lowercase()
            },
            leadingIcon = {
                IconButton(onClick = {
                    onAddClickIngredient(ingredient)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "addIngredient")
                }
            },

            label = { Text(text = "Ingredient") },
            placeholder = { Text(text = "Enter your ingredient") },
        )

        //ViewIngredients(oldIngredient)
        Text(text = "added: ")
        ViewIngredients(
            ingredients = ingredients,
            onDeleteIngredient = onDeleteIngredient
        )


        OutlinedTextField(value = steps, onValueChange = { steps = it },
            label = { Text(text = "steps") })

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                if (name.isNotEmpty() && images.isNotEmpty()
                    && difficulty.isNotEmpty() && description.isNotEmpty() &&
                    duration.isNotEmpty() && category.isNotEmpty() &&
                    steps.isNotEmpty()
                ) {

                    val newRecipe = Recipe(
                        oldRecipe.id, name, images, difficulty,
                        description, duration, category, (oldIngredient + ingredients), steps
                    )

                    onDeleteClickRecipe(oldRecipe)
                    onAddClickRecipe(newRecipe)
                    onNavigateClick("")

                }


            }) {

            Text(text = "Edit")
        }
    }

}

@Composable
fun TopAppBarWidget(navController: NavController){
    TopAppBar( backgroundColor = Header, elevation = 9.dp, modifier = Modifier.height(60.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp),
                onClick = {navController.navigate(route = AppScreens.HomeScreen.name)}) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home Screen")
            }
            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp)
                .width(60.dp),
                onClick = { navController.navigate(route = AppScreens.RecipesScreen.name) }) {
                Text(text = "Recipes", modifier = Modifier.fillMaxWidth())
            }

            Spacer(modifier = Modifier.width(10.dp))

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp)
                .width(80.dp),
                onClick = { navController.navigate(route = AppScreens.IngredientsScreen.name) }) {
                Text(text = "Ingredients", modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.width(10.dp))

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp)
                .width(98.dp),
                onClick = { navController.navigate(route = AppScreens.ShoppingListScreen.name) }) {
                //Icon(imageVector = Icons.Default.List, contentDescription = "Shopping List" )
                Text(text = "Shopping List")
            }

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp),
                onClick = {navController.navigate(route = AppScreens.AddRecipesScreen.name)}) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add Recipe")
            }

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp),
                onClick = { navController.navigate(route = AppScreens.FavoriteScreen.name)}) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "My Favorites")
            }

        }

    }
}



/*
@Composable
fun AddRecipeTest(onAddClick: (Recipe) -> Unit = {}) {

    //var ingredient by remember { mutableStateListOf<String>() }

    //OutlinedTextField(value = ingredient, onValueChange = {value -> ingredient = ingredient})


    //val ingredient by remember { mutableStateOf(mutableListOf<String>(TextFieldValue)) }
    //var ingredient by remember { mutableStateListOf<String>() }

    val data = remember {
        mutableStateListOf<String>()
    }

    LazyColumn() {
        items(items = ingredient) { ing,index ->
            OutlinedTextField(
                value = ing.,
                leadingIcon = {
                    IconButton(onClick = {
                        val newIngredient = Recipe(
                            id, name,
                            images, difficulty, description, duration, category,
                            ingredient, steps
                        )
                        onAddClick(newIngredient)
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "addIngredient")
                    }
                },
                //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                onValueChange = {

                                value -> ing[index] = value
                },
                label = { Text(text = "Ingredient") },
                placeholder = { Text(text = "Enter your ingredient") },
            )
        }
    }




    //var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            VectorIcon(imageVector = Icons.Default.Email)
        },
        modifier = inputModifier.border(
            BorderStroke(
                width = 4.dp,
                brush = Brush.horizontalGradient(listOf(Purple200, Purple500))
            ),
            shape = RoundedCornerShape(50)
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = inputTextStyle
    )


}

*/
