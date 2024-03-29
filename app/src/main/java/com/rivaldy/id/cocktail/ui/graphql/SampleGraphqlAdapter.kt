package com.rivaldy.id.cocktail.ui.graphql

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rivaldy.id.cocktail.R
import com.rivaldy.id.cocktail.databinding.RowItemCocktailBinding
import com.rivaldy.id.core.CharacterListQuery
import com.rivaldy.id.core.extensions.getDiffCallback

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

class SampleGraphqlAdapter(
    private val listener: (CharacterListQuery.Result) -> Unit
) : ListAdapter<CharacterListQuery.Result, SampleGraphqlAdapter.ViewHolder>(getDiffCallback()) {

    inner class ViewHolder(private val binding: RowItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: CharacterListQuery.Result) {
            val urlImage = item.image
            Glide.with(binding.root.context)
                .load(urlImage)
                .placeholder(R.color.colorDividerHigh)
                .into(binding.imageIV)
            binding.titleTV.text = item.name
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
}