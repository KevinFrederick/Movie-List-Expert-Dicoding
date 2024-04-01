package com.kevinfre.movielist.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kevinfre.core.domain.usecase.MovieUseCase

class FavoriteViewModel (
    movieUseCase: MovieUseCase
): ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}