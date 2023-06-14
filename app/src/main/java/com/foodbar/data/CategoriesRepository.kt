package com.foodbar.data

import com.foodbar.data.models.ResponseCategories
import com.squareup.moshi.Moshi
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCategories(): ResponseCategories? {

            val request = apiService.getCategories()
            if (request.isSuccessful) {
                return request.body()!!
            }
            return null

    }
}