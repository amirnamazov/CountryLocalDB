package com.example.countrylocaldb.presentation.filter

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.viewModels
import com.example.countrylocaldb.databinding.FragmentFilterBinding
import com.example.countrylocaldb.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate),
    OnClickListener {

    private val viewModel: FilterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        binding.vm = viewModel
        binding.btnConfirm.setOnClickListener(this)
    }

    private fun setupAdapter() {
        val adapter = FilterAdapter(viewModel)
        binding.rvFilter.adapter = adapter
        adapter.submitList(viewModel.filterModels)
    }

    override fun onClick(v: View?) {
        viewModel.publishSelectedCountries()
//        findNavController().navigateUp()
    }
}