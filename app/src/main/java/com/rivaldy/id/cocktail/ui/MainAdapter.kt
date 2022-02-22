package com.rivaldy.id.cocktail.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rivaldy.id.cocktail.R
import com.rivaldy.id.cocktail.databinding.RowItemCocktailBinding
import com.rivaldy.id.core.data.model.api.drink.DrinkData

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

class MainAdapter(
    private val listener: (DrinkData) -> Unit
) : ListAdapter<DrinkData, MainAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: RowItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: DrinkData) {
            val urlImage = "${item.strDrinkThumb}/preview"
            Glide.with(binding.root.context)
                .load(urlImage)
                .placeholder(R.color.colorDividerHigh)
                .into(binding.imageIV)
            binding.titleTV.text = item.strDrink
            binding.root.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DrinkData>() {
            override fun areItemsTheSame(oldItem: DrinkData, newItem: DrinkData): Boolean {
                return oldItem.idDrink == newItem.idDrink
            }

            override fun areContentsTheSame(oldItem: DrinkData, newItem: DrinkData): Boolean {
                return oldItem == newItem
            }
        }
    }
}