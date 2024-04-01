package com.kevinfre.movielist.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinfre.core.data.Resource
import com.kevinfre.core.ui.MovieAdapter
import com.kevinfre.movielist.R
import com.kevinfre.movielist.databinding.ActivityMainBinding
import com.kevinfre.movielist.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val mLayoutManager = LinearLayoutManager(this)

        val movieAdapter = MovieAdapter { data ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, data)
            startActivity(intent)
        }

        mainViewModel.movie.observe(this) {movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> activityMainBinding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        activityMainBinding.progressBar.visibility = View.GONE
                        mainViewModel.searchQuery.observe(this) {query ->
                            if (query.isBlank()) {
                                movieAdapter.submitList(movie.data)
                            } else {
                                movieAdapter.submitList(movie.data?.filter {
                                    it.title.contains(query, ignoreCase = true)
                                })
                            }
                        }
                    }
                    is Resource.Error -> {
                        activityMainBinding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "On Failure" + movie.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        with(activityMainBinding.rvMovies) {
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        with(activityMainBinding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { v, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    mainViewModel.setSearch(searchView.text.toString())
                    false
                }

            searchBar.inflateMenu(R.menu.option_menu)
            searchBar.setOnMenuItemClickListener{menuItem ->
                when(menuItem.itemId) {
                    R.id.menu_favorite -> {
                        val uri = Uri.parse("movielist://favorite")
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                        true
                    }
                    else -> false
                }
            }
        }
    }
}