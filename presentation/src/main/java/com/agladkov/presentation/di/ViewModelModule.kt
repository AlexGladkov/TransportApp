package com.agladkov.presentation.di

import androidx.lifecycle.ViewModelProvider
import com.agladkov.presentation.helpers.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}