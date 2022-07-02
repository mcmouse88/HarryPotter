package com.mcmouse88.harrypotter.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mcmouse88.harrypotter.data.di.appComponent
import com.mcmouse88.harrypotter.databinding.FragmentFavoriteBinding
import com.mcmouse88.harrypotter.presentation.rvadapter.MainAdapter
import com.mcmouse88.harrypotter.presentation.viewmodel.FavoriteViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.factory.FavoriteViewModelFactory
import dagger.Lazy
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw NullPointerException("FragmentFavoriteBinding is null")

    @Inject
    lateinit var factory: Lazy<FavoriteViewModelFactory>

    private val viewModel: FavoriteViewModel by viewModels { factory.get() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

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
            adapter.submitList(it.asReversed())
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