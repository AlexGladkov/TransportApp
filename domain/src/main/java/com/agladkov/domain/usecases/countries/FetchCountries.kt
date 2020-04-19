package com.agladkov.domain.usecases.countries

import com.agladkov.domain.usecases.UseCase
import com.agladkov.domain.usecases.countries.models.CountryModel
import javax.inject.Inject

class FetchCountries: UseCase<Unit, List<CountryModel>> {

    override suspend fun execute(
        request: Unit?,
        onSuccess: (List<CountryModel>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess.invoke(
            listOf(
                CountryModel(title = "Austria"),
                CountryModel(title = "Australia")
            )
        )
    }

}