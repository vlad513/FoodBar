package com.foodbar.data


import com.foodbar.data.models.ResponseCategories
import com.foodbar.data.models.ResponseDishes
import retrofit2.HttpException
import javax.inject.Inject

class DishesRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getDishes(): ResponseDishes? {
            val request = apiService.getDishes()
            if (request.isSuccessful) {
                return request.body()!!
            }
            return null
    }
}