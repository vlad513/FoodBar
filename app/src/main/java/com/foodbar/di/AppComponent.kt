package com.foodbar.di


import com.foodbar.ui.categories.CategoriesViewModel
import com.foodbar.ui.dishes.DishesViewModel
import com.foodbar.ui.dishes_dialog.DishesDialogViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun categoriesviewmodel():CategoriesViewModel
    fun dishesviewmodel():DishesViewModel



}