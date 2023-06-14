package com.foodbar.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes_table")
data class DishesEntity(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val price: Int?,
    val weight: Int?,
    val description: String?,
    val image_url: String?,
    val count:Int?
)

