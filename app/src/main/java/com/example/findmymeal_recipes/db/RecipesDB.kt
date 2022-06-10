package com.example.findmymeal_recipes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.findmymeal_recipes.models.Recipe

@Database(
    entities = [Recipe::class], // add entities here
    version = 1 ,               // wenn sich entity ändert müssen wir version erhöhen
    exportSchema = false        // Datenbankschema in einem File exportiert
)
abstract class RecipesDB: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao             // Jede Entity hat ein eigenes DAO, z.B. extra DAO für User.

    companion object {                             //Datenbankinstanz in Form eines Singleton erstellen --> Statische Methode
        private var INSTANCE: RecipesDB? = null

        fun getDatabase(context: Context): RecipesDB{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also{        // Wenn es keine Instanz gibt: buildDatabase Funktion aufrufen
                    INSTANCE = it                               // Wenn es eine gibt oder nicht, wird eine Datenbank generiert: Instance wird returned.
                }
            }
        }
        private fun buildDatabase(context: Context): RecipesDB{
            return Room.databaseBuilder(context, RecipesDB::class.java, "recipe_database")  // jede Android App lebt in einem bestimmten Contect / Acitvity,
                .addCallback(                          // eine Room Datenbank hat mehrere Callback Methoden ( start der app,..) --> Was passieren soll wenn diese Callback erreicht werden
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase){
                            super.onCreate(db)
                            // do work on first db creation
                        }
                        override fun onOpen(db: SupportSQLiteDatabase){         // beim App Start: sind Daten beim Remote Zugriff geändert worden? z.B.  dann cachen
                            super.onOpen(db)
                            // do work on each start
                        }
                    }
                )
                .fallbackToDestructiveMigration()       // Wenn Version erhöt wird, wird Migration Strategie verfolgt. Datenbank löschen und neu aufsetzen
                .build()
        }
    }
}