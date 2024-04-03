package com.kevinfre.core.data

import com.kevinfre.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

object DataDummy {
    fun generateDummyResponse(): Flow<Resource<List<Movie>>> {
        val items: MutableList<Movie> = arrayListOf()
        for (i in 0..10) {
            val movie = Movie(
                movieId = i,
                title = "Movie $i",
                description = "Description $i",
                poster = "Poster $i",
                isFavorite = Random.nextBoolean()
            )
            items.add(movie)
        }
        return flow {
            emit(Resource.Success(items))
        }
    }
}