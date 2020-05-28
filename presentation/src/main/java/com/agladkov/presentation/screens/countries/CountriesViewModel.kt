package com.agladkov.presentation.screens.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agladkov.domain.usecases.cities.FetchCities
import com.agladkov.domain.usecases.countries.FetchCountries
import kotlinx.android.synthetic.main.countries_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val fetchCountries: FetchCountries,
    private val fetchCities: FetchCities
): ViewModel() {

    val countriesText: MutableLiveData<String> = MutableLiveData("")

    fun fetchData() {
        GlobalScope.launch {
            fetchCities.execute(request = null, onSuccess = { text ->
               countriesText.postValue(text)
            }, onFailure = { errorMessage ->
                countriesText.postValue(errorMessage)
            })
        }
    }
}