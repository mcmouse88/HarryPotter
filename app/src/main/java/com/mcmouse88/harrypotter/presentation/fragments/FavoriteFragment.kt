package com.mcmouse88.harrypotter.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mcmouse88.harrypotter.databinding.FragmentFavoriteBinding
import com.mcmouse88.harrypotter.presentation.rvadapter.MainAdapter
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.factory.MainViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw NullPointerException("FragmentFavoriteBinding is null")

    private val factory by lazy {
        MainViewModelFactory(requireActivity().application)
    }

    private val viewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBackFavorite.setOnClickListener {
            findNavController().popBackStack()
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = MainAdapter()
        val rvFavorite = binding.rvFavoriteFragment
        rvFavorite.adapter = adapter
        viewModel.listFromDb.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        getDetailInfo(adapter)
    }

    private fun getDetailInfo(adapter: MainAdapter) {
        adapter.characterItemClick = {
            viewModel.getDetailCharacter(it) { character ->
                findNavController().navigate(
                    FavoriteFragmentDirections
                        .actionFavoriteFragmentToDetailFragment(character)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}