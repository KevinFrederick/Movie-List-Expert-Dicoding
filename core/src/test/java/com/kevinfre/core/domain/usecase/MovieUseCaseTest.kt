package com.kevinfre.core.domain.usecase

import com.kevinfre.core.data.DataDummy
import com.kevinfre.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    @Mock
    private lateinit var movieRepository: IMovieRepository

    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setUp(){
        movieUseCase = MovieInteractor(movieRepository)
        val dummyMovieList = DataDummy.generateDummyResponse()
        `when`(movieRepository.getAllMovie()).thenReturn(dummyMovieList)
    }

    @Test
    fun `Get all movie should not null and return data`() = runTest{
        val listMovies = movieUseCase.getAllMovie()

        Assert.assertEquals(true, !listMovies.first().data.isNullOrEmpty())
        Assert.assertEquals("Movie 0", listMovies.first().data?.get(0)?.title)
    }
}