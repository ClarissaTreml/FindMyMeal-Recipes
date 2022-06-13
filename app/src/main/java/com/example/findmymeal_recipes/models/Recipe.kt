package com.example.findmymeal_recipes.models



data class Recipe(
    val id: String,
    val name: String,
    val images: String,
    val difficulty: String, //Stars //Numbers(as String)
    val description: String,
    val duration: String,
    val category: String,
    val ingredients: List<String>,
    val steps: String,
    //Portion
)

//Recipe
fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            id = "1",
            name = "Chocolate Cake",
            images =
                "https://hips.hearstapps.com/vidthumb/images/flourless-chocolate-cake-horizontal-1549916637.png?crop=0.668xw:1.00xh;0.151xw,0&resize=768:*",
            difficulty = "Pro",
            description = "The best part about this flourless cake is that it doesn't require any flour alternatives. It's just the perfect cake that happens to have zero flour. " +
                    "Cocoa powder and eggs give it all the lift and structure it needs. Elegant and sleek, this is the cake I make most whenever I'm having dinner guests or need to" +
                    " throw together a quick and easy dessert for a party. It's can be made ahead of time, takes minimal effort, any gluten-free guests can still enjoy, and most " +
                    "importantly it always elicits oohs and aahs when I pour the ganache on just before serving. ",
            duration = "1h 30m",
            category = "Dessert",
            ingredients = listOf(
                "chocolate",
                "spinach",
                "heavy cream",
                "cocoa powder"
            ),
            steps = "Mix it ALL TOGETHER"
        ),
        Recipe(
            id = "2",
            name = "Pancakes",
            images =
                "https://www.einfachbacken.de/sites/einfachbacken.de/files/styles/1500_1130/public/2020-08/american_pancakes.jpg?h=4521fff0&itok=o7lZn-O0",

            difficulty = "Beginner",
            description = "A pancake is a flat cake, often thin and round, prepared from a starch-based batter that may contain eggs, milk and butter and cooked on a hot surface such as a griddle or frying pan, " +
                    "often frying with oil or butter. It is a type of batter bread.\n",
            duration = "30m",
            category = "Breakfast",
            ingredients = listOf(
                "garlic",
                "milk",
                "flour",
                "honey"
            ),
            steps = "Mix it ALL TOGETHER"
        ),
        Recipe(
            id = "3",
            name = "Vegetable Stew",
            images =
                "https://vancouverwithlove.com/wp-content/uploads/2019/12/Easy-Vegetable-Stew-Vancouver-with-Love-4.jpg",

            difficulty = "Advanced",
            description = "bla bla bla",
            duration = "1h 30m",
            category = "Lunch",
            ingredients = listOf(
                "tomato",
                "potato",
                "mushroom",
                "carrot"
            ),
            steps = "Mix it ALL TOGETHER",
        ),
    )
}

//Rezepte zum Anlegen:

/*
Pizza: https://www.gutekueche.at/storage/media/recipe/47760/pizza-selbstgemacht.jpg
*/

/*
Lasagne: https://www.gourmet-magazin.de/fileadmin/_processed_/d/e/csm_lasagne-1a_11cd711541.jpg
 */