package com.foodbar.ui.dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodbar.data.CategoriesRepository
import com.foodbar.data.DishesRepository
import com.foodbar.data.models.Dishes
import com.foodbar.data.models.ResponseCategories
import com.foodbar.data.models.ResponseDishes
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesViewModel
@Inject
constructor(private val repository: DishesRepository) :
    ViewModel() {
    private val _dishesByLiveData = MutableLiveData<ResponseDishes?>()
    val dishesByLiveData: LiveData<ResponseDishes?> = _dishesByLiveData

    var selection: Int? = null
    fun getDishes() {
        viewModelScope.launch(Dispatchers.IO+coroutineExceptionHandler) {
            val response = repository.getDishes()
            var listDishes = arrayListOf<Dishes>()
            response!!.dishes.forEach {
                when (selection) {
                    0 -> {
                        response.dishes.forEach { dishes ->
                            dishes.tegs.forEach { tegs ->
                                if (tegs == "Все меню") {
                                    listDishes.add(dishes)
                                }
                            }
                        }
                    }
                    1 -> {
                        response.dishes.forEach { dishes ->
                            dishes.tegs.forEach { tegs ->
                                if (tegs == "Салаты") {
                                    listDishes.add(dishes)
                                }
                            }
                        }
                    }
                    2 -> {
                        response.dishes.forEach { dishes ->
                            dishes.tegs.forEach { tegs ->
                                if (tegs == "С рисом") {
                                    listDishes.add(dishes)
                                }
                            }
                        }
                    }
                    3 -> {
                        response.dishes.forEach { dishes ->
                            dishes.tegs.forEach { tegs ->
                                if (tegs == "С рыбой") {
                                    listDishes.add(dishes)
                                }
                            }
                        }
                    }
                }
                val listDishesNoRepiate = listDishes.distinct()

                _dishesByLiveData.postValue(ResponseDishes(listDishesNoRepiate))
            }
        }
    }
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()

    }
}
