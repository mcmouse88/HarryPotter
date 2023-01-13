package com.mcmouse88.harrypotter.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FragmentMainBinding
import com.mcmouse88.harrypotter.presentation.rvadapter.MainAdapter
import com.mcmouse88.harrypotter.presentation.viewmodel.BaseViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.BaseViewModel.StateScreen
import com.mcmouse88.harrypotter.presentation.viewmodel.MainViewModel
import com.mcmouse88.harrypotter.utils.observeFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw NullPointerException("FragmentMainBinding is null")

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        setupRecyclerView()
        binding.ivFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        }
        setupObserver()
    }

    private fun setupRecyclerView() {
        val rvCharacterMain = binding.rvMainFragment
        val adapter = MainAdapter()
        rvCharacterMain.adapter = adapter
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listCharacter.collectLatest {
                    adapter.submitList(it)
                }
            }
        }

        getDetailInfo(adapter)
    }

    private fun setupObserver() {

        viewModel.screenState.observeFlow(viewLifecycleOwner) { state ->
            binding.rvMainFragment.isVisible = state is StateScreen.Content
            binding.progress.root.isVisible = state is StateScreen.Loading
            binding.empty.root.isVisible = state is StateScreen.Empty
        }

        viewModel.commands.observeFlow(viewLifecycleOwner) { command ->
            when (command) {
                is BaseViewModel.Commands.ShowErrorMessage -> {
                    Snackbar.make(binding.root, command.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getDetailInfo(adapter: MainAdapter) {
        adapter.characterItemClick = {
            viewModel.getDetailCharacter(it) { character ->
                findNavController().navigate(
                    MainFragmentDirections
                        .actionMainFragmentToDetailFragment(character)
                )
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}