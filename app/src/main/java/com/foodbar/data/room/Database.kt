package com.foodbar.data.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.foodbar.data.room.model.DishesEntity

@Database(entities = [DishesEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun Dao(): Dao

    companion object {
        fun getDatabase(context: Context): com.foodbar.data.room.Database {
            return Room.databaseBuilder(
                context.applicationContext,
                com.foodbar.data.room.Database::class.java,
                "database"
            ).build()
        }
    }
}