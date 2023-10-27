package com.sampleandroidapp.domain.usecase

import com.sampleandroidapp.domain.repository.MovieRepository

/**
 * @author by Ali Asadi on 13/08/2022
 */
class AddMovieToFavorite(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) = movieRepository.addMovieToFavorite(movieId)
}