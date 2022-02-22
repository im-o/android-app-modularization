package com.rivaldy.id.cocktail.ui.filter_dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rivaldy.id.cocktail.databinding.RowItemFilterBinding
import com.rivaldy.id.core.data.model.local.FilterDataLocal

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

class FilterAdapter(
    private val listener: (FilterDataLocal) -> Unit
) : ListAdapter<FilterDataLocal, FilterAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: RowItemFilterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: FilterDataLocal) {
            binding.filterNameTV.text = item.name
            binding.root.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FilterDataLocal>() {
            override fun areItemsTheSame(oldItem: FilterDataLocal, newItem: FilterDataLocal): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: FilterDataLocal, newItem: FilterDataLocal): Boolean {
                return oldItem == newItem
            }
        }
    }
}