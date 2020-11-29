package com.agladkov.presentation.screens.countries

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.agladkov.presentation.R
import com.agladkov.presentation.di.DaggerPresentationComponent
import com.agladkov.presentation.navigation.getNavigationResult
import com.agladkov.presentation.screens.countries.adapter.CountryAdapter
import com.agladkov.presentation.screens.countries.adapter.CountryAdapterClicks
import com.agladkov.presentation.screens.countries.adapter.CountryCellModel
import com.agladkov.presentation.screens.countries.models.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.android.synthetic.main.countries_fragment.*

@AndroidEntryPoint
class CountriesFragment : Fragment(R.layout.countries_fragment) {

    private val viewModel: CountriesViewModel by viewModels()
    lateinit var router: CountriesRoutingLogic

    private val adapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router =
            CountriesRouter(fragment = this)
        adapter.countryAdapterClicks = object : CountryAdapterClicks {
            override fun onItemClick(model: CountryCellModel) {
                if (model.title == "Russia") {
                    router.routeToCities(country = model)
                } else {
                    router.routeToWrongCountryAlert()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val result = getNavigationResult<String>(key = "Test")
        Log.e("TAG", "result $result")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewStates().observe(viewLifecycleOwner, Observer { bindViewState(it) })
        viewModel.viewEffects().observe(viewLifecycleOwner, Observer { bindViewAction(it) })
        viewModel.obtainEvent(CountriesEvent.ScreenShown)

        itemsView.adapter = adapter
        itemsView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,
            false
        )

        addMoreView.setOnClickListener { viewModel.obtainEvent(CountriesEvent.MoreClick) }
    }

    private fun bindViewAction(viewAction: CountriesAction) {
        when (viewAction) {
            is CountriesAction.ShowSnackbar -> Toast.makeText(
                context, viewAction.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun bindViewState(viewState: CountriesViewState) {
        when (viewState.fetchStatus) {
            FetchStatus.Loading -> print("Loading")
            FetchStatus.Success -> showContent(data = viewState.data)
            FetchStatus.AddMore -> addContent(data = viewState.data)
        }
    }

    private fun showContent(data: List<CountryCellModel>) {
        adapter.setData(data)
    }

    private fun addContent(data: List<CountryCellModel>) {
        adapter.addData(data)
    }
}