package com.example.countrylocaldb.presentation.filter

import android.os.Bundle
import android.view.View
import com.example.countrylocaldb.databinding.FragmentFilterBinding
import com.example.countrylocaldb.presentation.base.BaseFragment

class FilterFragment : BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate),
    FilterAdapter.ItemClick {

    private val list = List(43) { FilterModel((it + 1).toLong(), "Aziz ${it + 1}") }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        setupCheckAll()
    }

    private fun setupAdapter() {
        binding.rvFilter.adapter = FilterAdapter(this).apply {
            submitList(list)
        }
    }

    private fun setupCheckAll() = with(binding.ctvAll) {
        setOnClickListener {
            isChecked = !isChecked
            list.forEach { filter -> filter.checked.set(isChecked) }
        }
    }

    override fun onItemClicked(filter: FilterModel) = with(filter.checked) {
        set(!get())
        isAllChecked()
    }

    private fun isAllChecked() {
        binding.ctvAll.isChecked = list.size == list.filter { it.checked.get() }.size
    }
}