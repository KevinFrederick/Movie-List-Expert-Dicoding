package com.kevinfre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val description: String,
    val poster: String,
    val isFavorite: Boolean
): Parcelable