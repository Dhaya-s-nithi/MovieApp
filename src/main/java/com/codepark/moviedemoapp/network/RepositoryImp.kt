package com.codepark.moviedemoapp.network

import retrofit2.Callback

class RepositoryImp: Repository {
    private val networkApi: NetworkApi by lazy { CoreNetwork.getApiService(NetworkApi::class.java) }
    override  fun getMovieList(searchQuery: String, callBackListener: Callback<List<MovieListResponse>>){
        networkApi.getMovies(searchQuery).enqueue(callBackListener)
    }
}