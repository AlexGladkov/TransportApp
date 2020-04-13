package com.agladkov.transportapp.dagger

import com.agladkov.transportapp.screens.main.MainActivity
import com.agladkov.transportapp.screens.main.MainFragment
import com.agladkov.transportapp.screens.splash.SplashFragment
import dagger.Component

@Component(modules = [ApplicationModule::class, ScreenModule::class, VMModule::class])
interface AppComponent {

    // Fragments
    fun inject(fragment: MainFragment)

    // Activity
    fun inject(activity: MainActivity)
}