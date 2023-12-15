package com.example.countrylocaldb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countrylocaldb.databinding.ItemPeopleBinding
import com.example.countrylocaldb.domain.model.People

class PeopleAdapter : ListAdapter<People, PeopleAdapter.PeopleViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val entity = getItem(position)
        if (entity != null) holder.bind(entity)
    }

    class PeopleViewHolder(private val binding: ItemPeopleBinding) : ViewHolder(binding.root) {
        fun bind(entity: People) {
            binding.fullName = entity.run { "$name $surname" }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<People>() {
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
                oldItem == newItem
        }
    }
}