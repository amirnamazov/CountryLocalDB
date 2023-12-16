package com.example.countrylocaldb.presentation.people

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.countrylocaldb.R
import com.example.countrylocaldb.databinding.FragmentPeopleBinding
import com.example.countrylocaldb.presentation.MainViewModel
import com.example.countrylocaldb.presentation.base.BaseFragment

class PeopleFragment : BaseFragment<FragmentPeopleBinding>(FragmentPeopleBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setPeopleList()
        setupRvPeople()

        binding.btnCountryFilter.setOnClickListener {
            findNavController().navigate(R.id.fromPeopleToFilterFrag)
        }
    }

    private fun setupRvPeople() {
        val peopleAdapter = PeopleAdapter().also { binding.rvPeople.adapter = it }
        viewModel.liveDataPeople.observe(viewLifecycleOwner) { peopleList ->
//            println("76585786   ${peopleList.size}")
            peopleAdapter.submitList(peopleList)
        }
    }
}