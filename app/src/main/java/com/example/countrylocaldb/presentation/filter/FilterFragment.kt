package com.example.countrylocaldb.presentation.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.countrylocaldb.databinding.FragmentFilterBinding
import com.example.countrylocaldb.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate),
    FilterAdapter.ItemClick {

    private val viewModel: FilterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        setupCheckAll()
    }

    private fun setupAdapter() {
        val adapter = FilterAdapter(this)
        binding.rvFilter.adapter = adapter
        viewModel.liveDataCountries.observe(viewLifecycleOwner) { countries ->
            println("232432321   ${countries.size}")
            adapter.submitList(countries)
        }
    }

    private fun setupCheckAll() = with(binding.ctvAll) {
        setOnClickListener {
            isChecked = !isChecked
            viewModel.checkAllOptions(isChecked)
        }
    }

    override fun onItemClicked(filter: FilterModel) = with(filter.checked) {
        set(!get())
        isAllChecked()
    }

    private fun isAllChecked() = with(viewModel.getFilters()) {
        binding.ctvAll.isChecked = size == filter { it.checked.get() }.size
    }
}