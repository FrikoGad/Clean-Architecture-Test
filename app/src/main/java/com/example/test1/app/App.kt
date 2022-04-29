package com.example.test1.app

import android.app.Application
import com.example.test1.di.AppComponent
import com.example.test1.di.AppModule
import com.example.test1.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}