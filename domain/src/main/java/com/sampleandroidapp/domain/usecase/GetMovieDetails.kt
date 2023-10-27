package com.sampleandroidapp.domain.usecase

import com.sampleandroidapp.domain.entities.MovieEntity
import com.sampleandroidapp.domain.repository.MovieRepository
import com.sampleandroidapp.domain.util.Result

/**
 * Created by Ali Asadi on 13/05/2020
 **/
class GetMovieDetails(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<MovieEntity> = movieRepository.getMovie(movieId)
}
