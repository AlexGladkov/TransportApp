package com.agladkov.presentation.screens.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.agladkov.presentation.R
import com.agladkov.presentation.helpers.injectViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.countries_fragment.*
import javax.inject.Inject

class CountriesFragment : Fragment() {

    companion object {
        fun newInstance() = CountriesFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CountriesViewModel

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
        viewModel = injectViewModel(factory = viewModelFactory)
        viewModel.fetchData()

        bindModel()
    }

    private fun bindModel() {
        viewModel.countriesText.observe(viewLifecycleOwner, Observer { txtCountriesTest.text = it })
    }
}