package com.example.findmymeal_recipes.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.findmymeal_recipes.models.Recipe

class RecipeViewModel : ViewModel() {
    private var _recipes = mutableStateListOf<Recipe>()

    val recipes : List<Recipe>
        get() = _recipes

    fun addRecipe(recipe: Recipe){
        _recipes.add(recipe)
    }

    fun removeRecipe(recipe: Recipe){
        _recipes.remove(recipe)
    }

    fun getAllRecipes(): List<Recipe>{
        return _recipes
    }

    fun filterRecipes(){

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