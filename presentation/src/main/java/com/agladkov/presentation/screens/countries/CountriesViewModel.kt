package com.agladkov.presentation.screens.countries

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import com.agladkov.domain.usecases.countries.FetchCountries
import com.agladkov.presentation.base.BaseViewModel
import com.agladkov.presentation.screens.countries.adapter.mapToCountryCellModel
import com.agladkov.presentation.screens.countries.models.CountriesAction
import com.agladkov.presentation.screens.countries.models.CountriesEvent
import com.agladkov.presentation.screens.countries.models.CountriesViewState
import com.agladkov.presentation.screens.countries.models.FetchStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CountriesViewModel @ViewModelInject constructor(
    private val fetchCountriesUseCase: FetchCountries
) : BaseViewModel<CountriesViewState, CountriesAction, CountriesEvent>() {

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState = CountriesViewState(fetchStatus = FetchStatus.Loading, data = emptyList())
    }

    override fun obtainEvent(viewEvent: CountriesEvent) {
        when (viewEvent) {
            CountriesEvent.ScreenShown -> getInitialCountries()
            CountriesEvent.MoreClick -> loadMoreData()
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun loadMoreData() {
        val disposable = fetchCountriesUseCase.execute(request = Unit)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ countries ->
                viewState = CountriesViewState(
                    fetchStatus = FetchStatus.AddMore,
                    data = countries.map { it.mapToCountryCellModel() }
                )
            }, {
                viewAction = CountriesAction.ShowSnackbar(message = it.localizedMessage.orEmpty())
            })

        compositeDisposable.add(disposable)
    }

    private fun getInitialCountries() {
        viewState = CountriesViewState(fetchStatus = FetchStatus.Loading, data = emptyList())
        val disposable = fetchCountriesUseCase.execute(request = Unit)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ countries ->
                viewState = CountriesViewState(
                    fetchStatus = FetchStatus.Success,
                    data = countries.map { it.mapToCountryCellModel() }
                )
            }, {
                viewState.copy(
                    fetchStatus = FetchStatus.ShowError(message = it.localizedMessage.orEmpty())
                )
            })

        compositeDisposable.add(disposable)
    }
}