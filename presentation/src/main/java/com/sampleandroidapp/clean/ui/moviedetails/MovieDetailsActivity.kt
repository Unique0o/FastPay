package com.sampleandroidapp.clean.ui.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.sampleandroidapp.clean.MovieDetailsGraphDirections
import com.sampleandroidapp.clean.R
import com.sampleandroidapp.clean.databinding.ActivityMovieDetailsBinding
import com.sampleandroidapp.clean.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ali Asadi on 13/05/2020
 */

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding>() {

    private val args: MovieDetailsActivityArgs by navArgs()

    private val detailsNavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMovieDetailsBinding =
        ActivityMovieDetailsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
        detailsNavController.navigate(MovieDetailsGraphDirections.toMovieDetails(args.movieId))
    }

    private fun setupActionBar() = supportActionBar?.apply {
        setDisplayShowTitleEnabled(false)
        setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        fun start(context: Context, movieId: Int) {
            val starter = Intent(context, MovieDetailsActivity::class.java)
            starter.putExtra("movieId", movieId)
            context.startActivity(starter)
        }
    }
}