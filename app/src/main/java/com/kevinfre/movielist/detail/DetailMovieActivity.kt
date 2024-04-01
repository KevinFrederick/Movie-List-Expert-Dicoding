package com.kevinfre.movielist.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.kevinfre.core.domain.model.Movie
import com.kevinfre.movielist.R
import com.kevinfre.movielist.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModels()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        setSupportActionBar(toolbar)
        title = getString(R.string.movie_detail)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            binding.tvDetailDescription.text = detailMovie.description
            binding.tvDetailTitle.text = detailMovie.title
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original" + detailMovie.poster)
                .into(binding.ivDetailPoster)
            var isFavorite = detailMovie.isFavorite
            setStatusFavorite(isFavorite)
            binding.fab.setOnClickListener {
                isFavorite = !isFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, isFavorite)
                setStatusFavorite(isFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
