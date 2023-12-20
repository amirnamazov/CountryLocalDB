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

    protected val viewModel: FilterViewModel by viewModels()

    abstract val filters: List<FilterModel>

    abstract fun filterOptions()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        binding.frag = this
        binding.btnConfirm.setOnClickListener(this)
    }

    private fun setupAdapter() = with(FilterAdapter(this)) {
        binding.rvFilter.adapter = this
        submitList(filters)
    }

    override fun onClick(v: View?) {
        filterOptions()
        findNavController().navigateUp()
    }

    val allChecked by lazy {
        viewModel.run { ObservableBoolean(filters.isAllChecked()) }
    }

    fun onCheckAllClicked() = viewModel.run {
        allChecked.checkAll(filters)
    }

    override fun onItemClicked(filter: FilterModel) = viewModel.run {
        filter.check(allChecked, filters)
    }
}