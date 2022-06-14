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
)

//Recipe
fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            id = "1",
            name = "Chocolate Cake",
            images = "https://hips.hearstapps.com/vidthumb/images/flourless-chocolate-cake-horizontal-1549916637.png?crop=0.668xw:1.00xh;0.151xw,0&resize=768:*",
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
            images = "https://www.einfachbacken.de/sites/einfachbacken.de/files/styles/1500_1130/public/2020-08/american_pancakes.jpg?h=4521fff0&itok=o7lZn-O0",
            difficulty = "Beginner",
            description = "A pancake is a flat cake, often thin and round, prepared from a starch-based batter that may contain eggs, milk and butter and cooked on a hot surface such as a griddle or frying pan, " +
                    "often frying with oil or butter. It is a type of batter bread.\n",
            duration = "20 minutes total time \n" +
                    "5 minutes preparation time \n" +
                    "15 minutes cooking time\n",
            category = "Breakfast",
            ingredients = listOf(
                "flour",
                "baking powder",
                "salt",
                "sugar",
                "milk",
                "egg",
                "butter"
            ),
            steps = "Ingredients: \n" +
                    "1 ½ cups all-purpose flour\n" +
                    "3 ½ teaspoons baking powder\n" +
                    "¼ teaspoon salt, or more to taste\n" +
                    "1 tablespoon white sugar\n" +
                    "1 ¼ cups milk\n" +
                    "1 egg\n" +
                    "3 tablespoons butter, melted\n" +
                    "Preparation\n" +
                    "1: In a large bowl, sift together the flour, baking powder, salt and sugar. " +
                    "Make a well in the center and pour in the milk, egg and melted butter; " +
                    "mix until smooth. \n" +
                    "2: Heat a lightly oiled griddle or frying pan over medium-high heat. " +
                    "Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each" +
                    " pancake. Brown on both sides and serve hot."
        ),
        Recipe(
            id = "3",
            name = "Vegetable Stew",
            images = "https://vancouverwithlove.com/wp-content/uploads/2019/12/Easy-Vegetable-Stew-Vancouver-with-Love-4.jpg",
            difficulty = "Advanced",
            description = "Looking for fuss-free vegan dinner recipes? This one-pot Easy Vegetable Stew is delicious, healthy, hearty and takes less than an hour to make. It's perfect for vegan batch cooking.",
            duration = "Prep Time: 10 Minutes\n" +
                    "Cook Time: 25 Minutes\n" +
                    "Total TIme: 45 Minutes",
            category = "Lunch",
            ingredients = listOf(
                "tomato",
                "potato",
                "mushroom",
                "carrot",
                "onion",
                "celery stick",
                "broccoli",
                "vegetable stock",
                "water"
            ),
            steps = "Ingredients: \n" +
                    "1 onion (finely chopped)\n" +
                    "1 large stick of celery\n" +
                    "2 potatoes (chopped)\n" +
                    "2 carrots\n" +
                    "1 vegetable stock cube\n" +
                    "3 cups broccoli\n" +
                    "Preparation: \n" +
                    "1. In a large saucepan with a lid, steam sauté the onion in a small amount of water for 5 minutes on a medium heat. If it begins to stick, just add a bit more water and keep stirring." +
                    "2. Add the celery, potatoes, carrot, stock cube, and water to the pan and bring to the boil." +
                    "3. Reduce the heat and cook, covered, for 10 minutes, stirring now and then. The potato should be just tender." +
                    "4. Add the broccoli and cook for a further 10 minutes, stirring occasionally." +
                    "5. Serve"
        ),
        Recipe(
            id = "4",
            name = "Apple Cinnamon Oatmeal",
            images = "https://fitfoodiefinds.com/wp-content/uploads/2017/09/apple-cinnamon-oats-1.jpg",
            difficulty = "Beginner",
            description = "This Homemade Apple Cinnamon Oatmeal is an autumn inspired twist on plain oatmeal.",
            duration = "15 Minutes",
            category = "Breakfast",
            ingredients = listOf(
                "almond milk",
                "cinnamon",
                "apple",
                "oatmeal",
                "coconut oil",
                "pecans",
                "pomegranate seeds"
            ),
            steps = "Ingredients: \n" +
                    "1 tb coconut oil\n" +
                    "1 large apple\n" +
                    "1/2 tb cinnamon\n" +
                    "1/4 cup pecans\n" +
                    "1 cup oats, 2 cups almond milk\n" +
                    "1/4 cup pomegranate seeds (optional)\n" +
                    "Preparation: \n" +
                    "1. Heat coconut oil in a medium sized saucepan over medium heat.\n" +
                    "2. Add in the apples (cut into small chunks) and saute for 2-3 minutes. Stir in the cinnamon.\n" +
                    "3. Add in the pecans, oats, almond milk. Give it a stir and let it cook for 5-7 minutes, stirring constantly during the cooking process.\n" +
                    "4. Ladle into bowls and top them off with pomegranate seeds (or other fruit) and nuts.\n" +
                    "5. Serve"
        ),
        Recipe(
            id = "5",
            name = "Lentil Soup",
            images = "https://www.gimmesomeoven.com/wp-content/uploads/2020/05/Lemony-Lentil-Soup-Recipe-6.jpg",
            difficulty = "Advanced",
            description = "This Lemony Lentil Soup recipe is full of the best bright and zesty flavors, it’s naturally gluten-free and vegan, and it’s easy to make in the Instant Pot, Crock-Pot or on the stovetop.",
            duration = "Prep Time: 10 Minutes\n" +
                    "Cook Time: 35 Minutes\n" +
                    "Total Time: 35 Minutes",
            category = "Lunch",
            ingredients = listOf(
                "olive oil",
                "white onion",
                "carrots",
                "vegetable stock",
                "red lentils",
                "spices",
                "lemon"
            ),
            steps = "Ingredients: \n" +
                    "1 tb olive oil\n" +
                    "1 white onion (peeled and diced) \n" +
                    "2 carrots (diced) \n" +
                    "1 litre vegetable stock \n" +
                    "165g red lentils (rinsed) \n" +
                    "spices \n" +
                    "zest and juice of 1 lemon\n" +
                    "Preparation: \n" +
                    "1. Sauté the veggies in olice oil.\n" +
                    "2. Stir in the vegetable stock, lentils and spices. Then cover and cook for 15 minutes, stirring occasionally, until the lentils are completely tender.\n" +
                    "3. Blend the soup (optional).\n" +
                    "4. Seasons with lemon zest and juice. Taste and season the soup with a generous pinch or two of fine sea salt and black pepper, as needed.\n" +
                    "5. Serve"
        ),
        Recipe(
            id = "6",
            name = "Tiramisu",
            images = "https://images.lecker.de/,id=6d2e6b8d,b=lecker,w=610,cg=c.jpg",
            difficulty = "Beginner",
            description = "An Italian dessert made of Biscotti soaked in coffee, arranged in layers and filled with a cream made with mascarpone and eggs.\n",
            duration = "210 minutes total time \n" +
                    "30 min. preparation time \n" +
                    "180 min. cooking & resting time\n",
            category = "Dessert",
            ingredients = listOf(
                "egg",
                "powdered sugar",
                "mascarpone",
                "coffee",
                "biscotti",
                "cocoa"
            ),
            steps = "Ingredients: \n" +
                    "4 eggs\n" +
                    "100g powdered sugar\n" +
                    "500g mascarpone\n" +
                    "125ml coffee\n" +
                    "60pcs. biscotti\n" +
                    "6 tablespoons cocoa for sprinkling\n" +
                    "Preparation\n" +
                    "1: To make the tiramisu, start by separating the yolks and egg whites and placing them in separate bowls.\n" +
                    "2: Beat the egg whites with a hand mixer or food processor until stiff.\n" +
                    "3: Then beat the yolks in the other bowl with the powdered sugar until fluffy.\n" +
                    "4: Next, stir the mascarpone into the yolk mixture and then fold in the snow.\n" +
                    "5: Fill the coffee in a shallow bowl or plate and briefly soak the biscotti in it, one after the other.\n" +
                    "Then alternate layers of biscotti and mascarpone mixture in a mold - finish with the mascarpone cream.\n" +
                    "Now place the tiramisu in the refrigerator for at least 2.5 - 3 hours.\n" +
                    "Before serving, sprinkle with cocoa and garnish with fresh berries if desired.\n" +
                    "\n"
        ),
        Recipe(
            id = "7",
            name = "Spanish Tortilla",
            images = "https://www.spanishfoodguide.com/wp-content/uploads/2021/06/Authentic-Spanish-Tortilla-Recipe-Tortilla-Espanola-735x490.png.webp",
            difficulty = "Beginner",
            description = "Is a traditional spanish dish, very easy to make and you just need six ingredients. It consists mainly of potatoes, eggs and onions. ",
            duration = "50 minutes",
            category = "Dinner",
            ingredients = listOf(
                "potato",
                "egg",
                "onion",
                "salt",
                "olive oil",
                "green pepper",
            ),
            steps = "Ingredients: \n" +
                    "4 medium sized potatoes, 4 eggs, 1 white onion, 1tbs salt, and oliveoil, 1 green pepper (optional)"+
                    "\n"+
                    "1. Peel and cut the potatoes in thin slices.\n"+
                    "2. Cut the onions and the green pepper into cubes.\n"+
                    "3. Stir eggs in a bowl with a tbs of salt.\n"+
                    "4. Caramelize the onions in a pan with a little oil.\n"+
                    "5. Then add the green pepper.\n"+
                    "6. Then add the eggs.\n"+
                    "7. Put in with the lid on, in the oven for about 30 minutes at 180°C\n"
        ),

        Recipe(
            id = "8",
            name = "Avocado Egg Toast",
            images = "https://fitelo.co/wp-content/uploads/elementor/thumbs/avocado-toast-1-p10mrpsol6m0k06409eo26o7t99l954hcfm9lpf18w.jpg",
            difficulty = "Beginner",
            description = "A vegetarian dinner.",
            duration = "20 minutes",
            category = "Dinner",
            ingredients = listOf(
                "toast",
                "egg",
                "avocado",
                "salt",
                "olive oil",
                "pepper",
                "garlic",
            ),
            steps = "Ingredients: \n" +
                    "2 slices of toast, 2 eggs, salt, pepper, and olive oil"+
                    "\n"+
                    "1. Put both slices of toast in the toaster for 4 min\n"+
                    "2. Peal the avocado and cut it into slices.\n"+
                    "3. With a bit of olive oil fry two eggs.\n"+
                    "4. Meanwhile rub the garlic on the toast.\n"+
                    "5. Place the avocado slices on both toast.\n"+
                    "6. Place the eggs on the toasts.\n"+
                    "7. Season it with salt and pepper.\n"
        ),
        Recipe(
            id = "1",
            name = "Sacher Torte",
            images = "https://www.sacher.com/de/wp-content/uploads/sites/2/2019/08/SacherTorte_Teaser_klein.jpg",
            difficulty = "Pro",
            description = "The Sachertorte is a chocolate cake made from Sachermasse with apricot jam and chocolate icing. It is considered a specialty of Viennese cuisine and is entered as such in the register of traditional foods.",
            duration = "Baking time: about 60 minutes \n" +
                    "Cooling time: about 20 minutes",
            category = "Dessert",
            ingredients = listOf(
                "dark chocolate",
                "vanilla pod",
                "butter",
                "powdered sugar",
                "egg",
                "sugar",
                "flour",
                "apricot jam",
                "whipped cream"
            ),
            steps = "Ingredients: \n" +
                    "130 g dark chocolate couverture (min. 55% cocoa)\n" +
                    "1 vanilla pod\n" +
                    "150 g melted butter\n" +
                    "100g powdered sugar\n" +
                    "6 Eggs\n" +
                    "100g granulated sugar\n" +
                    "140g Plain wheat flour\n" +
                    "200g Apricot jam\n" +
                    "200g granulated sugar\n" +
                    "150g dark chocolate coating (min. 55% cocoa)\n" +
                    "Unsweetened whipped cream for garnish\n" +
                    "Preparation: \n" +
                    "1: Preheat the oven to 170 °C. Line the bottom of the springform pan with baking paper, grease and the sides and dust with a little flour. For the chocolate dough, melt the couverture over a hot water bath and then let it cool down a bit.\n" +
                    "2: Slit the vanilla bean lengthwise and scrape out the pulp. Beat the softened butter with the powdered sugar and vanilla pulp using a hand mixer fitted with a whisk attachment until fluffy.\n" +
                    "3: Separate the eggs. Add the egg yolks to the butter mixture one at a time. Now gradually add the melted chocolate coating. Beat the egg whites with the caster sugar until stiff, then fold onto the butter and chocolate mixture. Sift the flour over the mixture, then fold in the flour and whisk the egg whites.\n" +
                    "4: Pour the mixture into the springform pan, smooth the top and bake in the oven (center) for 10 to 15 minutes, leaving the oven door open a finger's width. Then close the oven and bake the cake for about 50 minutes more.\n" +
                    "5: Remove the cake from the oven and loosen the sides of the springform pan. Carefully transfer the cake to a cake rack lined with baking paper and let it cool for about 20 minutes. Only then remove the baking paper, turn the cake over and let it cool on the cake rack.\n" +
                    "6: Cut the cake in half horizontally. Heat the jam and stir until smooth. Spread the top of both halves of the cake with the jam and slide them over each other. Spread the sides with jam as well.\n" +
                    "7: To make the glaze, place the caster sugar in a saucepan with 125 ml of water and boil over high heat for about 5 minutes. Remove the sugar syrup from the heat and allow to cool slightly. Coarsely chop the couverture, gradually add it to the syrup and stir until there is a thick liquid (see tip).\n" +
                    "8: Pour all the lukewarm glaze over the surface of the cake at once, spreading it quickly with a palette. Allow the glaze to set for a few hours. Serve the Sacher cake garnished with unsweetened whipped cream.\n"
        )
    )
}

// TODO: No double IDs adding allowed.


//Rezepte zum Anlegen:
/*
Pizza: https://www.gutekueche.at/storage/media/recipe/47760/pizza-selbstgemacht.jpg
*/

/*
Lasagne: https://www.gourmet-magazin.de/fileadmin/_processed_/d/e/csm_lasagne-1a_11cd711541.jpg
 */