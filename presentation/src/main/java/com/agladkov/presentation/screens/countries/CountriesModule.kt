package com.agladkov.presentation.screens.countries

import androidx.lifecycle.ViewModel
import com.agladkov.presentation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CountriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    internal abstract fun countriesViewModel(viewModel: CountriesViewModel): ViewModel
}