package com.example.findmymeal_recipes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.findmymeal_recipes.repositories.RecipeRepository


@Suppress("UNCHECKED_CAST")
class RecipeViewModelFactory(private val repository: RecipeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(RecipeViewModel::class.java)){
            return RecipeViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        //return super.create(modelClass)
    }
}
