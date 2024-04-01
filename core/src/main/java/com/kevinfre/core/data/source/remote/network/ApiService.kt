package com.kevinfre.core.data.source.remote.network

import com.kevinfre.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("popular?language=en&page=1")
    suspend fun getList(): ListMovieResponse
}
