package com.agladkov.domain.usecases.cities

import com.agladkov.domain.usecases.UseCase
import com.agladkov.domain.usecases.countries.models.CountryModel
import io.reactivex.Single
import javax.inject.Inject

class FetchCities @Inject constructor(): UseCase<Unit, List<CountryModel>> {

    override fun execute(request: Unit?): Single<List<CountryModel>> {
        return Single.just(
            listOf(
                CountryModel(title = "Russia"),
                CountryModel(title = "USA"),
                CountryModel(title = "Germany"),
                CountryModel(title = "Italy"),
                CountryModel(title = "Spain"),
                CountryModel(title = "France"),
                CountryModel(title = "Great Britain"),
                CountryModel(title = "Ireland"),
                CountryModel(title = "Austria"),
                CountryModel(title = "Netherlands")
            )
        )
    }

}