package com.kevinfre.movielist.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinfre.core.ui.MovieAdapter
import com.kevinfre.movielist.detail.DetailMovieActivity
import com.kevinfre.movielist.di.FavoriteModuleDependencies
import com.kevinfre.movielist.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.favToolbar
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        setSupportActionBar(toolbar)
        title = getString(R.string.fav_movie)

        val mlayoutManager = LinearLayoutManager(this)

        val movieAdapter = MovieAdapter {data ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, data)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this) {result ->
            if (result.isNotEmpty()) {
                movieAdapter.submitList(result)
            } else {
                binding.rvFavorites.visibility = View.GONE
                binding.favEmpty.visibility = View.VISIBLE
                binding.animEmpty.visibility = View.VISIBLE
            }
        }

        with(binding.rvFavorites) {
            layoutManager = mlayoutManager
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}