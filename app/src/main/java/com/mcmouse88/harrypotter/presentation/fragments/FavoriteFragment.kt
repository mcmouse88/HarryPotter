package com.mcmouse88.harrypotter.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FragmentFavoriteBinding
import com.mcmouse88.harrypotter.presentation.rvadapter.MainAdapter
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw NullPointerException("FragmentFavoriteBinding is null")

    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)
        binding.rvFavoriteFragment.adapter = adapter

        setupObserver()
        setupListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupListener() {
        binding.ivBackFavorite.setOnClickListener {
            findNavController().popBackStack()
        }

        adapter.characterItemClick = {
            viewModel.getDetailCharacter(it) { character ->
                findNavController().navigate(
                    FavoriteFragmentDirections
                        .actionFavoriteFragmentToDetailFragment(character)
                )
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.listFromDb.collect { list ->
                adapter.submitList(list)
            }
        }
    }
}