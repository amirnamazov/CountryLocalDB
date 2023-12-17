package com.example.countrylocaldb.presentation.people

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.countrylocaldb.R
import com.example.countrylocaldb.databinding.FragmentPeopleBinding
import com.example.countrylocaldb.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(FragmentPeopleBinding::inflate) {

    private val viewModel: PeopleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.initializePeopleList()
        setupRvPeople()

        binding.btnCountryFilter.setOnClickListener {
            findNavController().navigate(R.id.fromPeopleToFilterFrag)
        }
    }

    private fun setupRvPeople() {
        val peopleAdapter = PeopleAdapter().also { binding.rvPeople.adapter = it }
        viewModel.liveDataPeople.observe(viewLifecycleOwner) { peopleList ->
            peopleAdapter.submitList(peopleList)
        }
    }
}