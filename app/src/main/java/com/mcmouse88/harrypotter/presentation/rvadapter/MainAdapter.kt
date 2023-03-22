package com.mcmouse88.harrypotter.presentation.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.CharacterItemBinding
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.utils.Constants.FEMALE_STATUS

class MainAdapter : ListAdapter<Character, MainAdapter.MainAdapterViewHolder>(CharacterItemDiffCallBack()) {

    var characterItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        val binding = CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MainAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {

        val character = getItem(position)
        with(holder) {
            binding.apply {
                tvNameMain.text = character.name
                tvBirthdayMain.text = character.dateOfBirth
                tvSpeciesMain.text = character.species
                if (character.gender == FEMALE_STATUS) {
                    ivGenderMain.setImageResource(R.drawable.ic_female_24)
                } else {
                    ivGenderMain.setImageResource(R.drawable.ic_male_24)
                }
                Glide.with(root.context)
                    .load(character.image)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(ivImageMain)

                root.setOnClickListener { characterItemClick?.invoke(character) }
            }
        }
    }

    inner class MainAdapterViewHolder(
        val binding: CharacterItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class CharacterItemDiffCallBack : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
}