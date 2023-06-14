package com.foodbar
import android.app.Application
import com.foodbar.di.AppComponent
import com.foodbar.di.DaggerAppComponent


class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder().build()
    }

}