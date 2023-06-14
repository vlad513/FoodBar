package com.foodbar.data.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.foodbar.data.room.model.DishesEntity

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDishes(user: DishesEntity)

    @Query("SELECT * FROM dishes_table")
    fun readAllDishes():LiveData<List<DishesEntity>>

    @Update
    suspend fun updateDishes(user:DishesEntity)

    @Delete
    suspend fun deleteDishes(user:DishesEntity)
}