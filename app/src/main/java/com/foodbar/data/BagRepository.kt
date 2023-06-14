package com.foodbar.data

import androidx.lifecycle.LiveData
import com.foodbar.data.room.Dao
import com.foodbar.data.room.model.DishesEntity

class BagRepository(private val Dao: Dao) {

    val readAllBase : LiveData<List<DishesEntity>> = Dao.readAllDishes()


    suspend fun updateDishes(dishes: DishesEntity){
        Dao.updateDishes(dishes)
    }

    suspend fun deleteDishes(dishes: DishesEntity){
        Dao.deleteDishes(dishes)
    }
}