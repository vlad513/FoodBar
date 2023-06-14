package com.foodbar.di


import com.foodbar.data.ApiService
import com.foodbar.data.CategoriesRepository
import com.foodbar.data.DishesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object AppModule {
    @Provides
    fun provideService(): ApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val okHttpClient = OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        )
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()

        return retrofit.create(ApiService::class.java)

    }

    @Provides
    fun repositoryCategoty() = CategoriesRepository(apiService =provideService())

    @Provides
    fun repositoryDishes() = DishesRepository(apiService = provideService())

}