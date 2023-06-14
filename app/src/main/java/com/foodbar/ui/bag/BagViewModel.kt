package com.foodbar.ui.bag

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foodbar.data.BagRepository
import com.foodbar.data.room.Database
import com.foodbar.data.room.model.DishesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BagViewModel(aplication: Application) : AndroidViewModel(aplication) {
    var repositorDB: BagRepository
    val readAllBase: LiveData<List<DishesEntity>>


    init {
        val userDao = Database.getDatabase(aplication).Dao()
        repositorDB = BagRepository(userDao)
        readAllBase = repositorDB.readAllBase
    }


    fun updateDishes(dishes: DishesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorDB.updateDishes(dishes)

        }
    }

    fun deleteUser(dishes: DishesEntity) {
        if (dishes.count?.equals(0) == true) {
            viewModelScope.launch(Dispatchers.IO) {
                repositorDB.deleteDishes(dishes)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                repositorDB.updateDishes(dishes)

            }
        }
    }
}