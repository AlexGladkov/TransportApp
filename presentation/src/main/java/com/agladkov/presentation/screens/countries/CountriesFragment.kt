package com.agladkov.presentation.screens.countries

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agladkov.domain.usecases.cities.FetchCities
import com.agladkov.domain.usecases.countries.FetchCountries
import com.agladkov.presentation.R
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.countries_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesFragment : Fragment() {

    companion object {
        fun newInstance() = CountriesFragment()
    }

    private lateinit var viewModel: CountriesViewModel

    @Inject lateinit var fetchUseCase: FetchCountries
    @Inject lateinit var fetchCities: FetchCities

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.countries_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountriesViewModel::class.java)

        GlobalScope.launch {
            fetchCities.execute(request = null, onSuccess = { text ->
                txtCountriesTest.text = text
            }, onFailure = { errorMessage ->
                txtCountriesTest.text = errorMessage
            })
        }
    }

}