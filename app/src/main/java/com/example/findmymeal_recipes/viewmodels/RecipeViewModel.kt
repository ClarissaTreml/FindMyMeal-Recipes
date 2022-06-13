package com.example.findmymeal_recipes.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.models.getRecipes

class RecipeViewModel : ViewModel() {

    private var _recipes = mutableStateListOf<Recipe>()
    private var _ingredients = mutableStateListOf<String>()

    init {
        _recipes.addAll(
            getRecipes()
        )
    }

    val recipes: List<Recipe>
        get() = _recipes

    fun addRecipe(recipe: Recipe) {
        _recipes.add(recipe)
        Log.d("ADDED", _recipes.toList().toString())
    }

    fun removeRecipe(recipe: Recipe) {
        _recipes.remove(recipe)
    }

    fun getAllRecipes(): List<Recipe> {
        return _recipes
    }

    fun filterRecipes() {

    }

    val ingredientsRecipe: List<String>
        get() = _ingredients

    fun addIngredientsRecipe(ingredient: String) {
        _ingredients.add(ingredient)
        Log.d("ADDED", _ingredients.last().toString())
    }

    fun clearIngredientsList() {
        _ingredients.clear()
    }

    fun removeIngredientsRecipe(ingredient: String) {
        _ingredients.remove(ingredient)
    }
}

/*
filter nach difficulty, category
fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}

maybe:
fun sortNotes(){

    }
 */