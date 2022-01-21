package com.codepark.moviedemoapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("search/shows")
    fun getMovies(@Query("q") search: String): Call<List<MovieListResponse>>
}