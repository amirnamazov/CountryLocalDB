package com.example.countrylocaldb.presentation.people

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.countrylocaldb.R
import com.example.countrylocaldb.databinding.FragmentPeopleBinding
import com.example.countrylocaldb.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(FragmentPeopleBinding::inflate),
    SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private val viewModel: PeopleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.initializePeopleList()
        setupRvPeople()
        initializeListeners()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe
    fun onResponseEvent(state: PeopleResponseEvent) {
        binding.swipeRefresh.isRefreshing = state is PeopleResponseEvent.Loading
        if (state is PeopleResponseEvent.Error) showSnackBar(state.message)
    }

    private fun initializeListeners() = with(binding) {
        swipeRefresh.setOnRefreshListener(this@PeopleFragment)
        btnCountryFilter.setOnClickListener(this@PeopleFragment)
        btnCityFilter.setOnClickListener(this@PeopleFragment)
    }

    private fun setupRvPeople() = with(PeopleAdapter()) {
        binding.rvPeople.adapter = this
        viewModel.liveDataPeople.observe(viewLifecycleOwner) { peopleList ->
            submitList(peopleList)
        }
    }

    override fun onRefresh() { viewModel.setCountriesFromNetworkToLocalDb() }

    override fun onClick(v: View?) = findNavController().navigate(
        if (v?.id == binding.btnCountryFilter.id) R.id.fromPeopleToFilterCountryFrag
        else R.id.fromPeopleToFilterCityFrag
    )
}