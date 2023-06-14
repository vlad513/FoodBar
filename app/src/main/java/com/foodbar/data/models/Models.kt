package com.foodbar.data.models

data class ResponseCategories(
    val сategories: List<Categories?>
)

data class Categories(
    val id: Int?,
    val name: String?,
    val image_url: String?
)

data class ResponseDishes(
    val dishes: List<Dishes>
):java.io.Serializable

data class Dishes(
    val id: Int?,
    val name: String?,
    val price: Int?,
    val weight: Int?,
    val description: String?,
    val image_url: String?,
    val tegs: List<String>
):java.io.Serializable
