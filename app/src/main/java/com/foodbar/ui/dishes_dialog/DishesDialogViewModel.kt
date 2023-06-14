package com.foodbar.ui.dishes_dialog

import android.app.Application
import androidx.lifecycle.*
import com.foodbar.data.DishesDialogRepository
import com.foodbar.data.room.Database
import com.foodbar.data.room.model.DishesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishesDialogViewModel(aplication: Application): AndroidViewModel(aplication)  {
    var repositorDB:DishesDialogRepository

    init {
        val userDao = Database.getDatabase(aplication).Dao()
        repositorDB = DishesDialogRepository(userDao)
    }

    fun addDishes(dishes: DishesEntity){
        viewModelScope.launch (Dispatchers.IO){
            repositorDB.addDishes(dishes)
        }
    }

}