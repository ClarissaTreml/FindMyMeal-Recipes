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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.findmymeal_recipes.R
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.navigation.AppScreens
import com.example.findmymeal_recipes.screens.detail.addToShoppingList
import com.example.findmymeal_recipes.ui.theme.Header
import com.example.findmymeal_recipes.ui.theme.ButtonColor

var init: String? = "All"
var difficulty: String? = null
var category: String? = null

@Composable
fun FilterRecipe(
    onScreenClick: (String) -> Unit = {},
) {
    var difficultyExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.width(130.dp)) {
        IconButton(modifier = Modifier
            .width(130.dp), onClick = { difficultyExpanded = true }) {
            Row() {
                Text(text = "by Difficulty", style = MaterialTheme.typography.h2)
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "Filter by Difficulty"
                )
            }
        }
        DropdownMenu(
            expanded = difficultyExpanded,
            onDismissRequest = { difficultyExpanded = false }
        ) {
            DropdownMenuItem(onClick = {
                difficulty = "Beginner"
                init = null
                category = null
                onScreenClick("")
            }) {
                Text(text = "Beginner", style = MaterialTheme.typography.h2)
            }
            Divider()
            DropdownMenuItem(onClick = {
                difficulty = "Advanced"
                init = null
                category = null
                onScreenClick("")
            }) {
                Text(text = "Advanced", style = MaterialTheme.typography.h2)
            }
            Divider()
            DropdownMenuItem(onClick = {
                difficulty = "Pro"
                init = null
                category = null
                onScreenClick("")
            }) {
                Text(text = "Pro", style = MaterialTheme.typography.h2)
            }
        }
        Divider()
        IconButton(modifier = Modifier
            .width(130.dp), onClick = { categoryExpanded = true }) {
            Row() {
                Text(text = "by Category", style = MaterialTheme.typography.h2)
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
                Text(text = "Breakfast", style = MaterialTheme.typography.h2)
            }
            Divider()
            DropdownMenuItem(onClick = {
                category = "Lunch"
                init = null
                difficulty = null
                onScreenClick("")
            }) {
                Text(text = "Lunch", style = MaterialTheme.typography.h2)
            }
            Divider()
            DropdownMenuItem(onClick = {
                category = "Dinner"
                init = null
                difficulty = null
                onScreenClick("")
            }) {
                Text(text = "Dinner", style = MaterialTheme.typography.h2)
            }
            Divider()
            DropdownMenuItem(onClick = {
                category = "Dessert"
                init = null
                difficulty = null
                onScreenClick("")
            }) {
                Text(text = "Dessert", style = MaterialTheme.typography.h2)
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
        )
    } else if (init == "All") {
        RecipeCards2(
            recipe = recipe,
            onItemClick = onItemClick,
            onDeleteClickRecipe = onDeleteClickRecipe,
            onAddRecipeToFavorite = onAddRecipeToFavorite,
            onDeleteOfFavorites = onDeleteOfFavorites,
            favoriteIcon = favoriteIcon,
            favorite = favorite
        )
    } else if (recipe.category.lowercase() == (category?.lowercase() ?: category)) {
        RecipeCards2(
            recipe = recipe,
            onItemClick = onItemClick,
            onDeleteClickRecipe = onDeleteClickRecipe,
            onAddRecipeToFavorite = onAddRecipeToFavorite,
            onDeleteOfFavorites = onDeleteOfFavorites,
            favoriteIcon = favoriteIcon,
            favorite = favorite
        )
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
                modifier = Modifier.fillMaxSize(),
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Header)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(300.dp)
                        .padding(20.dp, 0.dp)
                )
                {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.h4,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row() {
                        Text(
                            text = recipe.difficulty,
                            style = MaterialTheme.typography.h3
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = recipe.category,
                            style = MaterialTheme.typography.h3
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = recipe.description,
                        style = MaterialTheme.typography.h5
                    )
                }
                Column(modifier = Modifier.padding(0.dp, 15.dp)) {
                    IconButton(
                        onClick = { onItemClick(recipe.id) }) {
                        Image(
                            painterResource(R.drawable.fork),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Fork",
                            modifier = Modifier.size(30.dp)
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
            Icon(
                Icons.Default.Favorite,
                contentDescription = "FavoriteClicked",
                tint = Color.Yellow
            )
        }
    } else {
        IconButton(onClick = { onAddRecipeToFavorite(recipe) }) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "FavoriteNotClicked",
                tint = Color.Yellow
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(modifier = Modifier.padding(160.dp, 0.dp)) {
            IconButton(
                onClick = { onEditClick(recipe.id) },
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Button",
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.h4,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row() {
                Text(
                    text = recipe.difficulty,
                    style = MaterialTheme.typography.h3,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = recipe.category,
                    style = MaterialTheme.typography.h3,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = recipe.duration,
                style = MaterialTheme.typography.h5,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = recipe.description,
                style = MaterialTheme.typography.h5,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = recipe.steps,
                style = MaterialTheme.typography.h5,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                onClick = {
                    addToShoppingList(
                        recipe = recipe,
                        shoppingIngredient = shoppingIngredient,
                        onAddToShoppingList = onAddToShoppingList
                    )
                },
                interactionSource = interactionSource
            ) {
                Text(
                    if (isPressed) "Added to Shopping List" else "Add Ingredients To Shopping List",
                    style = MaterialTheme.typography.h3
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun ViewIngredients(
    ingredients: List<String> = listOf(),
    onDeleteIngredient: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .height(140.dp)
            .width(250.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy((-20).dp)) {
            items(ingredients) { ingredient ->
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onDeleteIngredient(ingredient) }) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Ingredient")
                        Text(text = ingredient, style = MaterialTheme.typography.subtitle1)
                    }
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        var id by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var images by remember { mutableStateOf("") }
        var difficulty by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var duration by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var ingredient by remember { mutableStateOf("") }
        var steps by remember { mutableStateOf("") }

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = id,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> id = value },
            label = { Text(text = "id", style = MaterialTheme.typography.h2, color = ButtonColor) })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = name,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> name = value },
            label = {
                Text(
                    text = "name",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = images,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> images = value },
            label = {
                Text(
                    text = "images",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = difficulty,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> difficulty = value },
            label = {
                Text(
                    text = "difficulty",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = description,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> description = value },
            label = {
                Text(
                    text = "description",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = duration,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> duration = value },
            label = {
                Text(
                    text = "duration",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = category,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> category = value },
            label = {
                Text(
                    text = "category",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = ingredient,
            textStyle = MaterialTheme.typography.h4,
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

            label = {
                Text(
                    text = "Ingredient",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            },
            placeholder = {
                Text(
                    text = "Enter your ingredient",
                    style = MaterialTheme.typography.h2
                )
            },
        )

        ViewIngredients(
            ingredients = ingredients,
            onDeleteIngredient = onDeleteIngredient
        )

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            value = steps,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { value -> steps = value },
            label = {
                Text(
                    text = "steps",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            }
        )
        Button(
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(Header),
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
            Text(
                text = "Add",
                style = MaterialTheme.typography.h3,
            )
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.verticalScroll(rememberScrollState())
    )
    {
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
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
            value = name,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { name = it },
            label = {
                Text(
                    text = "name",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
            value = images,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { images = it },
            label = {
                Text(
                    text = "images",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
            value = difficulty,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { difficulty = it },
            label = {
                Text(
                    text = "difficulty",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
            value = description,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { description = it },
            label = {
                Text(
                    text = "description",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
            value = duration,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { duration = it },
            label = {
                Text(
                    text = "duration",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
            value = category,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { category = it },
            label = {
                Text(
                    text = "category",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
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
            label = {
                Text(
                    text = "Ingredient",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            },
            placeholder = {
                Text(
                    text = "Enter your ingredient",
                    style = MaterialTheme.typography.h2
                )
            },
        )

        Column(modifier = Modifier.width(500.dp)) {
            Text(text = "added: ", style = MaterialTheme.typography.h2)
            ViewIngredients(
                ingredients = ingredients,
                onDeleteIngredient = onDeleteIngredient
            )
        }

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = ButtonColor),
            modifier = Modifier.width(500.dp),
            value = steps,
            textStyle = MaterialTheme.typography.h4,
            onValueChange = { steps = it },
            label = {
                Text(
                    text = "steps",
                    style = MaterialTheme.typography.h2,
                    color = ButtonColor
                )
            })

        Button(
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(Header),
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
            Text(text = "Edit", style = MaterialTheme.typography.h3)
        }
    }
}

@Composable
fun TopAppBarWidget(navController: NavController) {
    TopAppBar(backgroundColor = Header, elevation = 9.dp, modifier = Modifier.height(60.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp),
                onClick = { navController.navigate(route = AppScreens.HomeScreen.name) }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home Screen")
            }
            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp)
                .width(60.dp),
                onClick = { navController.navigate(route = AppScreens.RecipesScreen.name) }) {
                Text(
                    text = "Recipes",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h3
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp)
                .width(80.dp),
                onClick = { navController.navigate(route = AppScreens.IngredientsScreen.name) }) {
                Text(
                    text = "Ingredients",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h3
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp)
                .width(98.dp),
                onClick = { navController.navigate(route = AppScreens.ShoppingListScreen.name) }) {
                Text(text = "Shopping List", style = MaterialTheme.typography.h3)
            }

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp),
                onClick = { navController.navigate(route = AppScreens.AddRecipesScreen.name) }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add Recipe")
            }

            IconButton(modifier = Modifier
                .padding(0.dp, 12.dp),
                onClick = { navController.navigate(route = AppScreens.FavoriteScreen.name) }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "My Favorites",
                    tint = Color.Yellow
                )
            }
        }
    }
}