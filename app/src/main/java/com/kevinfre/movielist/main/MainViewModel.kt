package com.kevinfre.movielist.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kevinfre.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    movieUseCase: MovieUseCase
): ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String>
        get() = _searchQuery

    fun setSearch(query: String) {
        _searchQuery.value = query
    }
}