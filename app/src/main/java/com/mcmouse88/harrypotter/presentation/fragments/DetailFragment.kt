package com.mcmouse88.harrypotter.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.databinding.FragmentDetailBinding
import com.mcmouse88.harrypotter.presentation.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw NullPointerException("FragmentDetailBinding is null")

    private val args by navArgs<DetailFragmentArgs>()

    private val currentCharacter by lazy {
        args.characterArgs
    }

    private var isFavorite by Delegates.notNull<Boolean>()

    private val detailViewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillLayout()
        binding.ivBackDetail.setOnClickListener {
            findNavController().popBackStack()
        }
        addAndRemoveFromDb()
    }

    private fun fillLayout() {
        binding.apply {
            Glide.with(this@DetailFragment)
                .load(currentCharacter.image)
                .into(ivImageDetail)
            tvNameDetail.text = currentCharacter.name
            tvBirthdayDetail.text = getString(
                R.string.date_of_birthday,
                currentCharacter.dateOfBirth
            )
            tvSpeciesDetail.text = getString(R.string.species, currentCharacter.species)
            tvStatusDetail.text = getString(R.string.status, formatStatus())
            tvAncestryDetail.text = getString(R.string.ancestry, currentCharacter.ancestry)
            tvHouseDetail.text = getString(R.string.house, currentCharacter.house)
            if (isFavorite) ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_24)
            else ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_border_24)
            if (currentCharacter.gender == "female") {
                ivGenderDetail.setImageResource(R.drawable.ic_female_24)
            } else {
                ivGenderDetail.setImageResource(R.drawable.ic_male_24)
            }
            tvAppNameDetail.text = getString(R.string.character_name, currentCharacter.name)
        }
    }

    private fun addAndRemoveFromDb() {
        var isFavorite = isFavorite
        binding.ivFavoriteDetail.setOnClickListener {
            if (!isFavorite) {
                detailViewModel.addToFavorite(currentCharacter)
                binding.ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_24)
            } else {
                detailViewModel.deleteFromFavorite(currentCharacter)
                binding.ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_border_24)
            }
            isFavorite = !isFavorite
        }
    }

    private fun formatStatus(): String {
         return if (currentCharacter.alive) {
            "alive"
        } else {
            "dead"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
             isFavorite = detailViewModel.getCharacterFromDb(currentCharacter.name)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}