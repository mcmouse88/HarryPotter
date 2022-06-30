package com.mcmouse88.harrypotter.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val alive: Boolean,
    val ancestry: String,
    val dateOfBirth: String,
    val gender: String,
    val house: String,
    val image: String,
    val name: String,
    val species: String,
) : Parcelable