package com.example.findmymeal_recipes.models

data class Ingredients(
    val ingredient: String,
    val checked: Boolean
)

fun getIngredients(): List<Ingredients> {
    return listOf(
        Ingredients(ingredient = "tomato", checked = false),
        Ingredients(ingredient = "potato", checked = false),
        Ingredients(ingredient = "mushroom", checked = false),
        Ingredients(ingredient = "carrot", checked = false),
        Ingredients(ingredient = "cucumber", checked = false),
        Ingredients(ingredient = "zucchini", checked = false),
        Ingredients(ingredient = "broccoli", checked = false),
        Ingredients(ingredient = "lettuce", checked = false),
        Ingredients(ingredient = "onion", checked = false),
        Ingredients(ingredient = "garlic", checked = false),
        Ingredients(ingredient = "spring onion", checked = false),
        Ingredients(ingredient = "spinach", checked = false),
    )
}


