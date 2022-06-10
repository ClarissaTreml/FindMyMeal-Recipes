package com.example.findmymeal_recipes.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmymeal_recipes.models.Recipe
import com.example.findmymeal_recipes.repositories.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: RecipeRepository
): ViewModel() {
    //private var recipes = mutableStateListOf<Recipe>()
    private var _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllRecipes().collect{ listOfRecipes -> // Mit collect wird Recipe zu Liste, davor FLow
                if(listOfRecipes.isNullOrEmpty()){
                    // Threads laufen asynchron
                    // Wir ändern den Value von Recipe (observable) in einem anderen Thread, das triggerd recomposition der composables.
                    Log.d("RecipeViewModel", "No Recipes")
                } else{
                    _recipes.value = listOfRecipes
                }
            }
        }
    }

    fun addRecipe(recipe: Recipe) {
        // im Main Thread eine non Blocking Funktion starten == COROUTINE:
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecipe(recipe = recipe)
        } // Coroutines immer aus einem ViewModel starten. .launch startet Funktion aus dem Hintergrund.
    } // Dispatchers.IO --> CPU (?)

    fun removeRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecipe(recipe = recipe)
        }
    }

    fun editRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.editRecipe(recipe = recipe)
        }
    }



    fun sortRecipes(){

    }

    fun filterRecipes(){

    }


}