package com.foodbar.data

import com.foodbar.data.room.Dao
import com.foodbar.data.room.model.DishesEntity

class DishesDialogRepository(private val Dao: Dao) {

    suspend fun addDishes(dishes: DishesEntity){
        Dao.addDishes(dishes)
    }

    suspend fun updateUser(dishes: DishesEntity){
        Dao.updateDishes(dishes)
    }

}