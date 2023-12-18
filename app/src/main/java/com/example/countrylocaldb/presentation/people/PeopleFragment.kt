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

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(FragmentPeopleBinding::inflate),
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: PeopleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.initializePeopleList()
        setupRvPeople()

        binding.swipeRefresh.setOnRefreshListener(this)
        observeSwipeRefresh()

        binding.btnCountryFilter.setOnClickListener {
            findNavController().navigate(R.id.fromPeopleToFilterFrag)
        }
    }

    private fun setupRvPeople() = with(PeopleAdapter()) {
        binding.rvPeople.adapter = this
        viewModel.liveDataPeople.observe(viewLifecycleOwner) { peopleList ->
            submitList(peopleList)
        }
    }

    private fun observeSwipeRefresh() =
        viewModel.liveDataSwipeRefresh.observe(viewLifecycleOwner) { loading ->
            binding.swipeRefresh.isRefreshing = loading
        }

    override fun onRefresh() {
        viewModel.setCountriesFromNetworkToLocalDb()
    }
}