package com.mcmouse88.harrypotter.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FragmentFavoriteBinding
import com.mcmouse88.harrypotter.presentation.rvadapter.FavoriteAdapter
import com.mcmouse88.harrypotter.presentation.rvadapter.FavoriteItems
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import com.mcmouse88.harrypotter.utils.observeFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw NullPointerException("FragmentFavoriteBinding is null")

    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapter = initAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)
        binding.rvFavoriteFragment.adapter = adapter

        setupObserver()
        setupListener()
        setupSwipe()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupListener() {
        binding.ivBackFavorite.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupObserver() {
        viewModel.listFromDb.observeFlow(viewLifecycleOwner) { content ->
            when (content) {
                is FavoriteViewModel.ViewState.Content -> {
                    adapter.submitList(content.characters)
                    binding.loading.root.isVisible = false
                }
                is FavoriteViewModel.ViewState.NoContent -> {
                    adapter.submitList(content.placeHolder)
                    binding.loading.root.isVisible = false
                }
                FavoriteViewModel.ViewState.Loading -> {
                    binding.loading.root.isVisible = true
                }
            }
        }
    }

    private fun initAdapter(): FavoriteAdapter = FavoriteAdapter(
        eventListener = object : FavoriteAdapter.EventListener {

            override fun onItemClick(item: FavoriteItems.FillItems) {
                viewModel.getDetailCharacter(item.favorite) { character ->
                    findNavController().navigate(
                        FavoriteFragmentDirections
                            .actionFavoriteFragmentToDetailFragment(character)
                    )
                }
            }
        }
    )

    private fun setupSwipe() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition] as? FavoriteItems.FillItems ?: return
                viewModel.deleteCharacter(item.favorite)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvFavoriteFragment)
    }
}