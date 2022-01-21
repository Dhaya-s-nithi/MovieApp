package com.codepark.moviedemoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codepark.moviedemoapp.network.MovieListResponse
import com.codepark.moviedemoapp.network.Repository
import com.codepark.moviedemoapp.network.RepositoryImp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel: ViewModel() {
    private val repositoryImp: Repository = RepositoryImp()

    private val liveData: MutableLiveData<List<MovieListResponse>> = MutableLiveData()

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getLiveData(): MutableLiveData<List<MovieListResponse>>{
        return liveData
    }

    fun getErrorLiveData(): LiveData<String>{
        return errorLiveData
    }

    fun getMovieList(searchQuery: String){
        repositoryImp.getMovieList(searchQuery, object : Callback<List<MovieListResponse>> {
            override fun onResponse(call: Call<List<MovieListResponse>>,
                response: Response<List<MovieListResponse>>) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<List<MovieListResponse>>, t: Throwable) {
                errorLiveData.value = t.message
            }

        })
    }
}