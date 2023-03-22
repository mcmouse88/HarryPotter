package com.mcmouse88.harrypotter.presentation.rvadapter

import com.mcmouse88.harrypotter.domain.entity.Character

sealed interface FavoriteItems {
    data class FillItems(
        val favorite: Character
    ) : FavoriteItems {
        val name: String get() = favorite.name
        val dateOfBirth: String get() = favorite.dateOfBirth
        val species: String get() = favorite.species
        val gender: String get() = favorite.gender
        val image: String get() = favorite.image
    }

    object EmptyItem : FavoriteItems
}