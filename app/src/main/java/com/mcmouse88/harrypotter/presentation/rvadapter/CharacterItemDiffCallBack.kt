package com.mcmouse88.harrypotter.presentation.rvadapter

import androidx.recyclerview.widget.DiffUtil
import com.mcmouse88.harrypotter.domain.entity.Character

class CharacterItemDiffCallBack : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}