package com.agladkov.presentation.screens.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agladkov.presentation.R
import com.agladkov.presentation.helpers.injectViewModel
import com.agladkov.presentation.screens.countries.adapter.CountryAdapter
import com.agladkov.presentation.screens.countries.adapter.CountryCellModel
import com.agladkov.presentation.screens.countries.models.CountriesEvent
import com.agladkov.presentation.screens.countries.models.CountriesViewState
import com.agladkov.presentation.screens.countries.models.FetchStatus
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.countries_fragment.*
import javax.inject.Inject

class CountriesFragment : Fragment(R.layout.countries_fragment) {

    companion object {
        fun newInstance() = CountriesFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CountriesViewModel

    private val adapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(factory = viewModelFactory)
        viewModel.obtainEvent(CountriesEvent.ScreenShown)

        viewModel.viewStates().observe(viewLifecycleOwner, Observer { bindViewState(it) })

        itemsView.adapter = adapter
        itemsView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
            false)

        addMoreView.setOnClickListener { viewModel.obtainEvent(CountriesEvent.MoreClick) }
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