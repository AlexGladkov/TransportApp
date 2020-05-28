package com.agladkov.domain.usecases.cities

import com.agladkov.domain.usecases.UseCase

class FetchCities: UseCase<Unit, String> {
    override suspend fun execute(
        request: Unit?,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess.invoke("Cities Count 10")
    }

}