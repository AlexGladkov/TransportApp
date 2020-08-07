package com.agladkov.presentation.screens.cities

import androidx.lifecycle.ViewModel
import com.agladkov.presentation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CitiesModule {

    @Binds
    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    internal abstract fun citiesViewModel(viewModel: CitiesViewModel): ViewModel
}