package com.sampleandroidapp.data.repository.movie

import androidx.paging.PagingSource
import com.sampleandroidapp.data.db.movies.MovieDao
import com.sampleandroidapp.data.db.movies.MovieRemoteKeyDao
import com.sampleandroidapp.data.entities.MovieDbData
import com.sampleandroidapp.data.entities.MovieRemoteKeyDbData
import com.sampleandroidapp.data.entities.toDomain
import com.sampleandroidapp.data.exception.DataNotAvailableException
import com.sampleandroidapp.data.mapper.toDbData
import com.sampleandroidapp.data.util.DiskExecutor
import com.sampleandroidapp.domain.entities.MovieEntity
import com.sampleandroidapp.domain.util.Result
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Ali Asadi on 13/05/2020
 */
class MovieLocalDataSource(
    private val executor: DiskExecutor,
    private val movieDao: MovieDao,
    private val remoteKeyDao: MovieRemoteKeyDao,
) : MovieDataSource.Local {

    override fun movies(): PagingSource<Int, MovieDbData> = movieDao.movies()

    override suspend fun getMovies(): Result<List<MovieEntity>> = withContext(executor.asCoroutineDispatcher()) {
        val movies = movieDao.getMovies()
        return@withContext if (movies.isNotEmpty()) {
            Result.Success(movies.map { it.toDomain() })
        } else {
            Result.Error(DataNotAvailableException())
        }
    }

    override suspend fun getMovie(movieId: Int): Result<MovieEntity> = withContext(executor.asCoroutineDispatcher()) {
        return@withContext movieDao.getMovie(movieId)?.let {
            Result.Success(it.toDomain())
        } ?: Result.Error(DataNotAvailableException())
    }

    override suspend fun saveMovies(movieEntities: List<MovieEntity>) = withContext(executor.asCoroutineDispatcher()) {
        movieDao.saveMovies(movieEntities.map { it.toDbData() })
    }

    override suspend fun getLastRemoteKey(): MovieRemoteKeyDbData? = withContext(executor.asCoroutineDispatcher()) {
        remoteKeyDao.getLastRemoteKey()
    }

    override suspend fun saveRemoteKey(key: MovieRemoteKeyDbData) = withContext(executor.asCoroutineDispatcher()) {
        remoteKeyDao.saveRemoteKey(key)
    }

    override suspend fun clearMovies() = withContext(executor.asCoroutineDispatcher()) {
        movieDao.clearMoviesExceptFavorites()
    }

    override suspend fun clearRemoteKeys() = withContext(executor.asCoroutineDispatcher()) {
        remoteKeyDao.clearRemoteKeys()
    }
}