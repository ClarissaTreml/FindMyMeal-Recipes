package com.example.findmymeal_recipes.db

import androidx.room.*
import com.example.findmymeal_recipes.models.Recipe
import kotlinx.coroutines.flow.Flow

@Dao //Dao = Data Access Object
interface RecipeDao {

    @Insert
    suspend fun addRecipe(recipe: Recipe)

    @Update
    suspend fun editRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    // Warum hier kein suspend? Wegen Flow. Wird immer aktualisiert. Bei Veränderung der Daten wird es an den Composables weitergegebne. Der FLow ist schon eine Coroutine.
    @Query("SELECT * from Recipe")
    fun getRecipe(): Flow<List<Recipe>>

    @Query("DELETE FROM Recipe")
    suspend fun deleteAll()

    @Query("SELECT * from Recipe where id=:id")
    suspend fun getRecipeById(id: Long): Recipe

}