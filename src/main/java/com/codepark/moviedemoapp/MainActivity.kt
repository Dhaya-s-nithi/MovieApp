package com.codepark.moviedemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepark.moviedemoapp.network.MovieListResponse

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnClickListener {
    private lateinit var viewModel: MoviesViewModel
    private var recyclerview: RecyclerView? = null

    companion object {
        private const val DEFAULT_SEARCH_QUERY = "all"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        recyclerview = findViewById(R.id.recyclerView)
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovieList(DEFAULT_SEARCH_QUERY)
    }

    private fun initObserver(){
        viewModel.getLiveData().observe(this, {
            loadMovies(it)
        })

        viewModel.getErrorLiveData().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        return true
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
        when(item.title) {
            "Search" -> {
                navigateToSearchView()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToSearchView() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}