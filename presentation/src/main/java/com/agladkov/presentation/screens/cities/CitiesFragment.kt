package com.agladkov.presentation.screens.cities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agladkov.presentation.R
import com.agladkov.presentation.helpers.injectViewModel
import com.agladkov.presentation.navigation.setNavigationResult
import com.agladkov.presentation.screens.cities.models.CitiesAction
import com.agladkov.presentation.screens.cities.models.CitiesEvent
import com.agladkov.presentation.screens.cities.models.CitiesViewState
import com.agladkov.presentation.screens.cities.models.FetchStatus
import com.agladkov.presentation.screens.countries.adapter.CountryAdapter
import com.agladkov.presentation.screens.countries.adapter.CountryAdapterClicks
import com.agladkov.presentation.screens.countries.adapter.CountryCellModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.countries_fragment.*
import javax.inject.Inject

class CitiesFragment: Fragment(R.layout.fragment_cities) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CitiesViewModel

    private lateinit var router: CitiesRouter
    private val adapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        router = CitiesRouter(this)
        requireArguments()
            .get("CountryKey")
            ?.let {
                Log.e("TAG", "Country $it")
            }

        adapter.countryAdapterClicks = object: CountryAdapterClicks {
            override fun onItemClick(model: CountryCellModel) {
                setNavigationResult(key = "Test", value = "Result")
                router.routeToMain()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(factory = viewModelFactory)
        viewModel.viewStates().observe(viewLifecycleOwner, Observer { bindViewState(it) })
        viewModel.viewEffects().observe(viewLifecycleOwner, Observer { bindViewAction(it) })
        viewModel.obtainEvent(CitiesEvent.ScreenShown)

        itemsView.adapter = adapter
        itemsView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
            false)
    }

    private fun bindViewAction(viewAction: CitiesAction) {
        when (viewAction) {
            is CitiesAction.ShowSnackbar -> Toast.makeText(context, viewAction.message,
                Toast.LENGTH_LONG).show()
        }
    }

    private fun bindViewState(viewState: CitiesViewState) {
        when (viewState.fetchStatus) {
            FetchStatus.Loading -> print("Loading")
            FetchStatus.Success -> showContent(data = viewState.data)
        }
    }

    private fun showContent(data: List<CountryCellModel>) {
        adapter.setData(data)
    }
}