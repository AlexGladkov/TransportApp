package com.agladkov.presentation.di

import com.agladkov.domain.di.modules.UseCasesModule
import com.agladkov.presentation.screens.countries.CountriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [UseCasesModule::class])
abstract class ScreenBindingModule {

    @ContributesAndroidInjector
    abstract fun countriesFragment(): CountriesFragment
}