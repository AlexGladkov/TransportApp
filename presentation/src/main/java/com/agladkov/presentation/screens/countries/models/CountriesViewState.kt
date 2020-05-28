package com.agladkov.presentation.screens.countries.models

import com.agladkov.presentation.screens.countries.adapter.CountryCellModel

data class CountriesViewState(
    val fetchStatus: FetchStatus,
    val data: List<CountryCellModel>
)

sealed class FetchStatus {
    object Success : FetchStatus()
    object AddMore : FetchStatus()
    data class ShowError(val message: String) : FetchStatus()
    object Loading : FetchStatus()
}