package com.foodbar.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodbar.data.CategoriesRepository
import com.foodbar.data.models.ResponseCategories
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(private val repository: CategoriesRepository) :
    ViewModel() {
    private val _categotiesByLiveData = MutableLiveData<ResponseCategories?>()
    val categotiesByLiveData: LiveData<ResponseCategories?> = _categotiesByLiveData

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO +coroutineExceptionHandler) {
            val response = repository.getCategories()
            _categotiesByLiveData.postValue(response)
            }
    }
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()

    }
}