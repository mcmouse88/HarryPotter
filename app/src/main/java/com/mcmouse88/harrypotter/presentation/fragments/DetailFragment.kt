package com.mcmouse88.harrypotter.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FragmentDetailBinding
import com.mcmouse88.harrypotter.domain.entity.Character
import com.mcmouse88.harrypotter.domain.utils.Constants.FEMALE_STATUS
import com.mcmouse88.harrypotter.presentation.viewmodel.DetailViewModel
import com.mcmouse88.harrypotter.presentation.viewmodel.viewModelCreator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw NullPointerException("FragmentDetailBinding is null")

    private val args by navArgs<DetailFragmentArgs>()

    private var isFavorite by Delegates.notNull<Boolean>()

    @Inject
    lateinit var factory: DetailViewModel.Factory

    private val viewModel: DetailViewModel by viewModelCreator {
        factory.create(args.characterArgs.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        val currentCharacter = args.characterArgs
        setupObserver()
        fillLayout(currentCharacter)
        setupListeners(currentCharacter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.isFavorite.collect { isFavoriteFlow ->
                if (isFavoriteFlow) binding.ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_24)
                else binding.ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_border_24)
                isFavorite = isFavoriteFlow
            }
        }
    }

    private fun fillLayout(currentCharacter: Character) {
        binding.apply {
            Glide.with(this@DetailFragment)
                .load(currentCharacter.image)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(ivImageDetail)
            tvNameDetail.text = currentCharacter.name
            tvBirthdayDetail.text = getString(
                R.string.date_of_birthday,
                currentCharacter.dateOfBirth
            )
            tvSpeciesDetail.text = getString(R.string.species, currentCharacter.species)
            tvStatusDetail.text = getString(R.string.status, formatStatus(currentCharacter))
            tvAncestryDetail.text = getString(R.string.ancestry, currentCharacter.ancestry)
            tvHouseDetail.text = getString(R.string.house, currentCharacter.house)
            if (currentCharacter.gender == FEMALE_STATUS) {
                ivGenderDetail.setImageResource(R.drawable.ic_female_24)
            } else {
                ivGenderDetail.setImageResource(R.drawable.ic_male_24)
            }
            tvAppNameDetail.text = getString(R.string.character_name, currentCharacter.name)
        }
    }

    private fun setupListeners(currentCharacter: Character) {
        binding.ivFavoriteDetail.setOnClickListener {
            if (!isFavorite) {
                viewModel.addToFavorite(currentCharacter)
                binding.ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_24)
            } else {
                viewModel.deleteFromFavorite(currentCharacter)
                binding.ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_border_24)
            }
            isFavorite = !isFavorite
        }

        binding.ivBackDetail.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun formatStatus(currentCharacter: Character): String {
         return if (currentCharacter.alive) {
            getString(R.string.alive)
        } else {
            getString(R.string.dead)
        }
    }
}