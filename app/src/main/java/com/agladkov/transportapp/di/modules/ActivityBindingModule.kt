package com.agladkov.transportapp.di.modules

import com.agladkov.presentation.screens.countries.CountriesFragment
import com.agladkov.transportapp.screens.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}