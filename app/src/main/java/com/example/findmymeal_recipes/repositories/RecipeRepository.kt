package com.example.findmymeal_recipes.repositories

import com.example.findmymeal_recipes.db.RecipeDao
import com.example.findmymeal_recipes.models.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val dao: RecipeDao) {
    // ruft DAO auf.
    // wenn wir ein Recipe instanzieren soll ein DAO aufgerufen werden (?) Muss ich mir nochmal anhören

    suspend fun addRecipe(recipe: Recipe) = dao.addRecipe(recipe = recipe)

    suspend fun editRecipe(recipe: Recipe) = dao.editRecipe(recipe = recipe)

    suspend fun deleteRecipe(recipe: Recipe) = dao.deleteRecipe(recipe = recipe)

    suspend fun deleteAll() = dao.deleteAll()

    fun getAllRecipes(): Flow<List<Recipe>> = dao.getRecipe()

// suspend = eine Funktion die im Hintergrund sehr lange läuft. Soll aber andere nicht stören
// wenn eine suspen fun eine fun auffruft muss die zweite fun auch supend fun sein.
}