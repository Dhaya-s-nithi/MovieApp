package com.codepark.moviedemoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import com.bumptech.glide.Glide
import com.codepark.moviedemoapp.network.MovieListResponse

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar?.title = "Movie Detail"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val score: TextView = findViewById(R.id.score)
        score.text = detailsOfMovie?.score.toString()

        detailsOfMovie?.show?.let { lShow ->
            val image: ImageView = findViewById(R.id.thumbnail)
            Glide.with(this).load(lShow.image?.original).into(image)

            val title: TextView = findViewById(R.id.title)
            title.text = lShow.name

            val language: TextView = findViewById(R.id.language)
            language.text = lShow.language

            val genre: TextView = findViewById(R.id.genre)
            genre.text = TextUtils.join(", ", lShow.genres)

            val status: TextView = findViewById(R.id.status)
            status.text = lShow.status

            val runtime: TextView = findViewById(R.id.runtime)
            runtime.text = lShow.runtime.toString()

            val rating: TextView = findViewById(R.id.rating)
            rating.text = lShow.rating.toString()

            val schedule: TextView = findViewById(R.id.schedule)
            schedule.text = lShow.schedule.toString()

            val summary: TextView = findViewById(R.id.summary)
            summary.text = HtmlCompat.fromHtml(lShow.summary.toString(), FROM_HTML_MODE_COMPACT)

        }

    }
    companion object {
        private var detailsOfMovie: MovieListResponse? = null

        fun setMovieDetails(movieDetail: MovieListResponse) {
            detailsOfMovie = movieDetail
        }
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