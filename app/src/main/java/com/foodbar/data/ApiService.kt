package com.foodbar.data

import com.foodbar.data.models.ResponseCategories
import com.foodbar.data.models.ResponseDishes
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(
    ): Response<ResponseCategories>

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun getDishes(
    ): Response<ResponseDishes>
}