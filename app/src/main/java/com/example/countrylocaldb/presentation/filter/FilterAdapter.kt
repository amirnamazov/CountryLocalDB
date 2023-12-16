package com.example.countrylocaldb.presentation.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countrylocaldb.databinding.ItemFilterBinding

class FilterAdapter(private val itemClick: ItemClick) :
    ListAdapter<FilterModel, FilterAdapter.FilterViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterBinding.inflate(inflater, parent, false)
        return FilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = getItem(position)
        if (filter != null) holder.bind(filter)
    }

    inner class FilterViewHolder(private val binding: ItemFilterBinding) :
        ViewHolder(binding.root) {
        fun bind(filter: FilterModel) {
            binding.filter = filter
            binding.itemClick = itemClick
        }
    }

    interface ItemClick {
        fun onItemClicked(filter: FilterModel)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<FilterModel>() {
            override fun areItemsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FilterModel, newItem: FilterModel): Boolean =
                oldItem == newItem
        }
    }
}