package com.agladkov.domain.di.modules

import com.agladkov.domain.di.DomainScope
import com.agladkov.domain.usecases.UseCase
import com.agladkov.domain.usecases.cities.FetchCities
import com.agladkov.domain.usecases.countries.FetchCountries
import com.agladkov.domain.usecases.countries.models.CountryModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    fun provideFetchCountriesUseCase(): FetchCountries {
        return FetchCountries()
    }

    @Provides
    fun provideFetchCitiesUseCase(): FetchCities {
        return FetchCities()
    }
}