package com.sampleandroidapp.clean.presentation.moviedetails

import app.cash.turbine.test
import com.sampleandroidapp.clean.presentation.base.BaseViewModelTest
import com.sampleandroidapp.clean.presentation.util.mock
import com.sampleandroidapp.clean.ui.moviedetails.MovieDetailsViewModel
import com.sampleandroidapp.domain.entities.MovieEntity
import com.sampleandroidapp.domain.usecase.AddMovieToFavorite
import com.sampleandroidapp.domain.usecase.CheckFavoriteStatus
import com.sampleandroidapp.domain.usecase.GetMovieDetails
import com.sampleandroidapp.domain.usecase.RemoveMovieFromFavorite
import com.sampleandroidapp.domain.util.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Ali Asadi on 16/05/2020
 **/
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest : BaseViewModelTest() {

    private var movieId: Int = 1413

    private val movie = MovieEntity(movieId, "title", "desc", "image", "category")

    @Mock
    lateinit var getMovieDetails: GetMovieDetails

    @Mock
    lateinit var checkFavoriteStatus: CheckFavoriteStatus

    @Mock
    lateinit var addMovieToFavorite: AddMovieToFavorite

    @Mock
    lateinit var removeMovieFromFavorite: RemoveMovieFromFavorite

    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setUp() {
        viewModel = MovieDetailsViewModel(
            movieId = movieId,
            getMovieDetails = getMovieDetails,
            checkFavoriteStatus = checkFavoriteStatus,
            removeMovieFromFavorite = removeMovieFromFavorite,
            addMovieToFavorite = addMovieToFavorite,
            dispatchers = coroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun onInitialState_movieAvailable_showMovieDetails() = runTest {
        `when`(getMovieDetails(movieId)).thenReturn(Result.Success(movie))

        viewModel.onInitialState()

        viewModel.uiState.test {
            val emission = awaitItem()
            assertThat(emission.description).isEqualTo(movie.description)
            assertThat(emission.imageUrl).isEqualTo(movie.image)
            assertThat(emission.title).isEqualTo(movie.title)
            assertThat(emission.isFavorite).isFalse()
        }
    }

    @Test
    fun onInitialState_movieNotAvailable_doNothing() = runTest {
        `when`(getMovieDetails(movieId)).thenReturn(Result.Error(mock()))

        viewModel.onInitialState()

        viewModel.uiState.test {
            val emission = awaitItem()
            assertThat(emission).isEqualTo(MovieDetailsViewModel.MovieDetailsUiState())
        }
    }
}
