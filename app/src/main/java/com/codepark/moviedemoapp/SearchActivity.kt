package com.codepark.moviedemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepark.moviedemoapp.network.MovieListResponse
import com.google.android.material.textfield.TextInputEditText

class SearchActivity : AppCompatActivity(), RecyclerViewAdapter.OnClickListener {
    private lateinit var viewModel: MoviesViewModel
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val actionBar = supportActionBar
        actionBar?.title = "Search"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        recyclerview = findViewById(R.id.recyclerView)
        val searchText: TextInputEditText = findViewById(R.id.search)
        searchText.doAfterTextChanged {
            it?.let {
                viewModel.getMovieList(it.toString())
            }
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.getLiveData().observe(this, {
            loadMovies(it)
        })

        viewModel.getErrorLiveData().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun loadMovies(listOfMovie: List<MovieListResponse>) {
        recyclerview?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val recyclerViewAdapter = RecyclerViewAdapter(this, listOfMovie, this)
        recyclerview?.adapter = recyclerViewAdapter
    }

    override fun navigateDetails(movie: MovieListResponse) {
        DetailActivity.setMovieDetails(movie)
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}