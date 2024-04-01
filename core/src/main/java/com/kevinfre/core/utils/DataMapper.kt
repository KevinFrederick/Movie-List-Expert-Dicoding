package com.kevinfre.core.utils

import com.kevinfre.core.data.source.local.entity.MovieEntity
import com.kevinfre.core.data.source.remote.response.ResultsItem
import com.kevinfre.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                description = it.overview,
                poster = it.posterPath
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                description = it.description,
                poster = it.poster,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            movieId = input.movieId,
            title = input.title,
            description = input.description,
            poster = input.poster,
            isFavorite = input.isFavorite
        )
}