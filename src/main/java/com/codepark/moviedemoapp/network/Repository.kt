package com.codepark.moviedemoapp.network

import retrofit2.Callback

interface Repository {
    fun getMovieList(searchQuery: String, callBackListener: Callback<List<MovieListResponse>>)
}