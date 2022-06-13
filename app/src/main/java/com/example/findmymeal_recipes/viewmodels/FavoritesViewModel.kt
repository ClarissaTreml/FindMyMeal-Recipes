package com.example.findmymeal_recipes.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.findmymeal_recipes.models.Recipe

class FavoritesViewModel : ViewModel() {

    private val _favoriteRecipes = mutableStateListOf<Recipe>()

    val favoriteRecipe: List<Recipe>
        get() = _favoriteRecipes

    fun addFavorite(recipe: Recipe) {
        if (!existFavorite(recipe = recipe)) {
            _favoriteRecipes.add(recipe)
            Log.d("AddedF", _favoriteRecipes.toList().toString())
        }
    }

    fun removeFavorite(recipe: Recipe) {
        _favoriteRecipes.remove(recipe)
    }

    fun existFavorite(recipe: Recipe): Boolean {
        return _favoriteRecipes.any { recipes -> recipes.id == recipe.id }
    }

    fun isFavorite(recipe: Recipe): Boolean {
        return existFavorite(recipe = recipe)
    }
}