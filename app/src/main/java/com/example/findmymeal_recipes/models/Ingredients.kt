package com.example.findmymeal_recipes.models

data class Ingredients(
    val ingredient: String,
    //val checked: Boolean
)

fun getIngredients(): List<Ingredients> {
    return listOf(
        Ingredients(ingredient = "almond milk"),
        Ingredients(ingredient = "apple"),
        Ingredients(ingredient = "apricot jam"),
        Ingredients(ingredient = "avocado"),
        Ingredients(ingredient = "baking powder"),
        Ingredients(ingredient = "biscotti"),
        Ingredients(ingredient = "butter"),
        Ingredients(ingredient = "broccoli"),
        Ingredients(ingredient = "carrot"),
        Ingredients(ingredient = "celery stick"),
        Ingredients(ingredient = "chocolate"),
        Ingredients(ingredient = "cinnamon"),
        Ingredients(ingredient = "cocoa"),
        Ingredients(ingredient = "cocoa powder"),
        Ingredients(ingredient = "coconut oil"),
        Ingredients(ingredient = "coffee"),
        Ingredients(ingredient = "cucumber"),
        Ingredients(ingredient = "dark chocolate"),
        Ingredients(ingredient = "egg"),
        Ingredients(ingredient = "flour"),
        Ingredients(ingredient = "garlic"),
        Ingredients(ingredient = "green pepper"),
        Ingredients(ingredient = "heavy cream"),
        Ingredients(ingredient = "lemon"),
        Ingredients(ingredient = "lettuce"),
        Ingredients(ingredient = "mascarpone"),
        Ingredients(ingredient = "milk"),
        Ingredients(ingredient = "mushroom"),
        Ingredients(ingredient = "oatmeal"),
        Ingredients(ingredient = "olive oil"),
        Ingredients(ingredient = "onion"),
        Ingredients(ingredient = "pecans"),
        Ingredients(ingredient = "pepper"),
        Ingredients(ingredient = "pomegranate seeds"),
        Ingredients(ingredient = "potato"),
        Ingredients(ingredient = "powdered sugar"),
        Ingredients(ingredient = "red lentils"),
        Ingredients(ingredient = "salt"),
        Ingredients(ingredient = "spices"),
        Ingredients(ingredient = "spinach"),
        Ingredients(ingredient = "spring onion"),
        Ingredients(ingredient = "sugar"),
        Ingredients(ingredient = "toast"),
        Ingredients(ingredient = "tomato"),
        Ingredients(ingredient = "vanilla pod"),
        Ingredients(ingredient = "vegetable stock"),
        Ingredients(ingredient = "water"),
        Ingredients(ingredient = "whipped cream"),
        Ingredients(ingredient = "white onion"),
        Ingredients(ingredient = "zucchini"),
    )
}


