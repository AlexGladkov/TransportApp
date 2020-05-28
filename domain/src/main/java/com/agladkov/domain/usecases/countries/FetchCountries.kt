package com.agladkov.domain.usecases.countries

import com.agladkov.domain.usecases.UseCase
import com.agladkov.domain.usecases.countries.models.CountryModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class FetchCountries: UseCase<Unit, List<CountryModel>> {

    override suspend fun execute(
        request: Unit?,
        onSuccess: (List<CountryModel>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        delay(3000)
        onSuccess.invoke(
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