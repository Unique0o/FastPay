package com.sampleandroidapp.data.repository.movie.favorite

import androidx.paging.PagingSource
import com.sampleandroidapp.data.db.favoritemovies.FavoriteMovieDao
import com.sampleandroidapp.data.entities.FavoriteMovieDbData
import com.sampleandroidapp.data.entities.MovieDbData
import com.sampleandroidapp.data.exception.DataNotAvailableException
import com.sampleandroidapp.data.util.DiskExecutor
import com.sampleandroidapp.domain.util.Result
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * @author by Ali Asadi on 22/08/2022
 */
class FavoriteMoviesLocalDataSource(
    private val executor: DiskExecutor,
    private val favoriteMovieDao: FavoriteMovieDao,
) : FavoriteMoviesDataSource.Local {

    override fun favoriteMovies(): PagingSource<Int, MovieDbData> = favoriteMovieDao.favoriteMovies()

    override suspend fun addMovieToFavorite(movieId: Int) = withContext(executor.asCoroutineDispatcher()) {
        favoriteMovieDao.add(FavoriteMovieDbData(movieId))
    }

    override suspend fun removeMovieFromFavorite(movieId: Int) = withContext(executor.asCoroutineDispatcher()) {
        favoriteMovieDao.remove(movieId)
    }

    override suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean> = withContext(executor.asCoroutineDispatcher()) {
        return@withContext Result.Success(favoriteMovieDao.get(movieId) != null)
    }

    override suspend fun getFavoriteMovieIds(): Result<List<Int>> = withContext(executor.asCoroutineDispatcher()) {
        val movieIds = favoriteMovieDao.getAll().map { it.movieId }
        return@withContext if (movieIds.isNotEmpty()) {
            Result.Success(movieIds)
        } else {
            Result.Error(DataNotAvailableException())
        }
    }
}
