package com.agladkov.presentation.screens.cities

import androidx.hilt.lifecycle.ViewModelInject
import com.agladkov.domain.usecases.cities.FetchCities
import com.agladkov.presentation.base.BaseViewModel
import com.agladkov.presentation.screens.cities.models.CitiesAction
import com.agladkov.presentation.screens.cities.models.CitiesEvent
import com.agladkov.presentation.screens.cities.models.CitiesViewState
import com.agladkov.presentation.screens.cities.models.FetchStatus
import com.agladkov.presentation.screens.countries.adapter.mapToCountryCellModel
import com.agladkov.presentation.screens.countries.models.CountriesEvent
import com.agladkov.presentation.screens.countries.models.CountriesViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CitiesViewModel @ViewModelInject constructor(
    private val fetchCitiesUseCase: FetchCities
) : BaseViewModel<CitiesViewState, CitiesAction, CitiesEvent>() {

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState = CitiesViewState(fetchStatus = FetchStatus.Loading, data = emptyList())
    }

    override fun obtainEvent(viewEvent: CitiesEvent) {
        when (viewEvent) {
            CitiesEvent.ScreenShown -> getInitialCities()
        }
    }

    // Internal logic
    private fun getInitialCities() {
        viewState = CitiesViewState(fetchStatus = FetchStatus.Loading, data = emptyList())
        val disposable = fetchCitiesUseCase.execute(request = Unit)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ cities ->
                viewState = CitiesViewState(
                    fetchStatus = FetchStatus.Success,
                    data = cities.map { it.mapToCountryCellModel() }
                )
            }, {
                viewState.copy(
                    fetchStatus = FetchStatus.ShowError(message = it.localizedMessage.orEmpty())
                )
            })

        compositeDisposable.add(disposable)
    }
}