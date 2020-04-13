package com.agladkov.transportapp

import android.app.Application
import com.agladkov.transportapp.dagger.AppComponent
import com.agladkov.transportapp.dagger.ApplicationModule
import com.agladkov.transportapp.dagger.DaggerAppComponent

class TransportApp: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(application = this))
            .build()
    }
}