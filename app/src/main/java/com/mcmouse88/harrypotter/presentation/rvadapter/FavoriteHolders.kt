package com.mcmouse88.harrypotter.presentation.rvadapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FavoriteItemBinding
import com.mcmouse88.harrypotter.databinding.ItemEmptyFavoriteBinding
import com.mcmouse88.harrypotter.utils.Constants

class FavoriteHolder(
    private val binding: FavoriteItemBinding,
    private val eventListener: FavoriteAdapter.EventListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FavoriteItems.FillItems) {
        binding.apply {
            tvNameMain.text = item.name
            tvBirthdayMain.text = item.dateOfBirth
            tvSpeciesMain.text = item.species
            if (item.gender == Constants.FEMALE_STATUS) {
                ivGenderMain.setImageResource(R.drawable.ic_female_24)
            } else {
                ivGenderMain.setImageResource(R.drawable.ic_male_24)
            }
            Glide.with(root.context)
                .load(item.image)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(ivImageMain)

            root.setOnClickListener {
                eventListener.onItemClick(item)
            }
        }
    }
}

class EmptyFavoriteHolder(
    binding: ItemEmptyFavoriteBinding
) : RecyclerView.ViewHolder(binding.root)