package com.example.countrylocaldb.presentation.filter.fragments

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.countrylocaldb.databinding.FragmentFilterBinding
import com.example.countrylocaldb.presentation.base.BaseFragment
import com.example.countrylocaldb.presentation.filter.FilterAdapter
import com.example.countrylocaldb.presentation.filter.FilterModel
import com.example.countrylocaldb.presentation.filter.FilterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class FilterFragment : BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate),
    OnClickListener, FilterAdapter.ItemClick {

    val viewModel: FilterViewModel by viewModels()

    abstract fun getFilters(): List<FilterModel>

    abstract fun filterOptions()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        binding.frag = this
        binding.btnConfirm.setOnClickListener(this)
    }

    private fun setupAdapter() = with(FilterAdapter(this)) {
        binding.rvFilter.adapter = this
        submitList(getFilters())
    }

    override fun onClick(v: View?) {
        filterOptions()
        findNavController().navigateUp()
    }

    private fun allChecked() = getFilters().all { it.checked.get() }

    val isAllChecked by lazy { ObservableBoolean(allChecked()) }

    fun onCheckAllClicked() = with(isAllChecked) {
        set(!get())
        getFilters().forEach { it.checked.set(get()) }
    }

    override fun onItemClicked(filter: FilterModel) {
        getFilters().find { it == filter }?.checked?.let { it.set(!it.get()) }
        isAllChecked.set(allChecked())
    }
}