package com.example.findmymeal_recipes.models

data class Ingredients(
    val ingredient: String,
    //val checked: Boolean
)

fun getIngredients(): List<Ingredients> {
    return listOf(
        Ingredients(ingredient = "tomato"),
        Ingredients(ingredient = "potato"),
        Ingredients(ingredient = "mushroom"),
        Ingredients(ingredient = "carrot"),
        Ingredients(ingredient = "cucumber"),
        Ingredients(ingredient = "zucchini"),
        Ingredients(ingredient = "broccoli"),
        Ingredients(ingredient = "lettuce"),
        Ingredients(ingredient = "onion"),
        Ingredients(ingredient = "garlic"),
        Ingredients(ingredient = "spring onion"),
        Ingredients(ingredient = "spinach"),
    )
}


