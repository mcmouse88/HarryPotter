package com.mcmouse88.harrypotter.presentation.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FavoriteItemBinding
import com.mcmouse88.harrypotter.databinding.ItemEmptyFavoriteBinding

class FavoriteAdapter(
   private val eventListener: EventListener
) : ListAdapter<FavoriteItems, RecyclerView.ViewHolder>(FavoriteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            FILL_ITEM -> FavoriteHolder(
                binding = FavoriteItemBinding.inflate(inflater, parent, false),
                eventListener = eventListener
            )
            EMPTY_ITEM -> EmptyFavoriteHolder(
                ItemEmptyFavoriteBinding.inflate(inflater, parent, false)
            )
            else -> error("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is FavoriteItems.FillItems -> (holder as? FavoriteHolder)?.bind(item = item)
            FavoriteItems.EmptyItem -> { /* no-op */ }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FavoriteItems.FillItems -> FILL_ITEM
            FavoriteItems.EmptyItem -> EMPTY_ITEM
        }
    }

    interface EventListener {
        fun onItemClick(item: FavoriteItems.FillItems)
    }

    private class FavoriteDiffCallback : DiffUtil.ItemCallback<FavoriteItems>() {

        override fun areItemsTheSame(oldItem: FavoriteItems, newItem: FavoriteItems): Boolean {
            return if (oldItem is FavoriteItems.FillItems && newItem is FavoriteItems.FillItems) {
                oldItem.name == newItem.name
            } else false
        }

        override fun areContentsTheSame(oldItem: FavoriteItems, newItem: FavoriteItems): Boolean {
            return oldItem == newItem
        }
    }

    private companion object {
        private const val FILL_ITEM = R.layout.favorite_item
        private const val EMPTY_ITEM = R.layout.item_empty_favorite
    }
}