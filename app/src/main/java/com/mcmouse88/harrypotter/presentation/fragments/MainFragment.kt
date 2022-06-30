package com.mcmouse88.harrypotter.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FragmentMainBinding
import com.mcmouse88.harrypotter.presentation.rvadapter.MainAdapter
import com.mcmouse88.harrypotter.presentation.viewmodel.SplashViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw NullPointerException("FragmentMainBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getListCharacter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.ivFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        }
    }

    private fun setupRecyclerView() {
        val rvCharacterMain = binding.rvMainFragment
        val adapter = MainAdapter()
        rvCharacterMain.adapter = adapter
        viewModel.listCharacter.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        getDetailInfo(adapter)
    }

    private fun getDetailInfo(adapter: MainAdapter) {
        val navController = findNavController()
        adapter.characterItemClick = {
            viewModel.getDetailCharacter(navController, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}