package com.example.findmymeal_recipes.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.findmymeal_recipes.models.Ingredients

class ChoseIngredientsViewModel : ViewModel() {
    private val _chosenIngredients = mutableStateListOf<Ingredients>()

    val chosenIngredients: List<Ingredients>
        get() = _chosenIngredients

    fun addIngredient(ingredient: Ingredients) {
        if (!exists(ingredient = ingredient)) {
            _chosenIngredients.add(ingredient)
            Log.d("ADDED", "${_chosenIngredients.last()}")
        }
    }

    fun removeIngredient(ingredient: Ingredients) {
        _chosenIngredients.remove(ingredient)
        Log.d("Removed", "${_chosenIngredients.last()}")
    }

    private fun exists(ingredient: Ingredients): Boolean {
        return _chosenIngredients.any { ingredients -> ingredients == ingredient }
    }

    fun isClicked(ingredient: Ingredients): Boolean {
        return exists(ingredient = ingredient)
    }
}