package com.example.findmymeal_recipes.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ShoppingListViewModel : ViewModel() {

    private var _shoppingListIngredients = mutableStateListOf<String>()

    val shoppingIngredients: List<String>
        get() = _shoppingListIngredients

    fun addShoppingIngredient(shoppingIngredient: String) {
        _shoppingListIngredients.add(shoppingIngredient)
        Log.d("ADDED", _shoppingListIngredients.last())
    }

    fun removeShoppingIngredient(shoppingIngredient: String) {
        _shoppingListIngredients.remove(shoppingIngredient)
        Log.d("REMOVE", _shoppingListIngredients.last())
    }
}