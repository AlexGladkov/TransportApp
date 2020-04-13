package com.agladkov.transportapp.dagger

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.agladkov.domain.usecases.FetchBelgiumTransportUseCase
import com.agladkov.transportapp.screens.main.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class VMModule {

    @Provides
    fun providerViewModelFactory(application: Application): ViewModelProvider.Factory {
        return ViewModelProvider.AndroidViewModelFactory(application)
    }

    @Provides
    fun provideManViewModel(
        factory: ViewModelProvider.Factory,
        fetchBelgiumTransportUseCase: FetchBelgiumTransportUseCase
    ): MainViewModel {
        val viewModel = factory.create(MainViewModel::class.java)
        viewModel.fetchBelgiumTransportUseCase = fetchBelgiumTransportUseCase
        return viewModel
    }
}