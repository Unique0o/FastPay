package com.sampleandroidapp.domain.usecase

import com.sampleandroidapp.domain.repository.MovieRepository
import com.sampleandroidapp.domain.util.Result

/**
 * @author by Ali Asadi on 13/08/2022
 */
class CheckFavoriteStatus(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Boolean> = movieRepository.checkFavoriteStatus(movieId)
}