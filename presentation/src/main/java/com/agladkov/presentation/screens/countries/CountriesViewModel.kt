package com.agladkov.presentation.screens.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agladkov.domain.usecases.cities.FetchCities
import com.agladkov.domain.usecases.countries.FetchCountries
import com.agladkov.presentation.base.BaseViewModel
import com.agladkov.presentation.screens.countries.adapter.mapToCountryCellModel
import com.agladkov.presentation.screens.countries.models.CountriesAction
import com.agladkov.presentation.screens.countries.models.CountriesEvent
import com.agladkov.presentation.screens.countries.models.CountriesViewState
import com.agladkov.presentation.screens.countries.models.FetchStatus
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val fetchCountriesUseCase: FetchCountries
): BaseViewModel<CountriesViewState, CountriesAction, CountriesEvent>() {

    init {
        viewState = CountriesViewState(fetchStatus = FetchStatus.Loading, data = emptyList())
    }

    override fun obtainEvent(viewEvent: CountriesEvent) {
        when (viewEvent) {
            CountriesEvent.ScreenShown -> getInitialCountries()
            CountriesEvent.MoreClick -> loadMoreData()
        }
    }

    private fun loadMoreData() {
        viewModelScope.launch {
            fetchCountriesUseCase.execute(
                request = Unit,
                onSuccess = { countries ->
                    viewState = viewState.copy(
                        fetchStatus = FetchStatus.AddMore,
                        data = countries.map { it.mapToCountryCellModel() }
                    )
                },
                onFailure = {
                    viewAction = CountriesAction.ShowSnackbar(message = it)
                }
            )
        }
    }

    private fun getInitialCountries() {
        viewState = CountriesViewState(fetchStatus = FetchStatus.Loading, data = emptyList())
        viewModelScope.launch {
            fetchCountriesUseCase.execute(
                request = Unit,
                onSuccess = { countries ->
                    viewState = CountriesViewState(
                        fetchStatus = FetchStatus.Success,
                        data = countries.map { it.mapToCountryCellModel() }
                    )
                }, onFailure = {
                    viewState.copy(
                        fetchStatus = FetchStatus.ShowError(message = it)
                    )
                })
        }
    }
}