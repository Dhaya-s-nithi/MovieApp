package com.codepark.moviedemoapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoreNetwork {
    private lateinit var mBuilder: Retrofit
    init {
        initNetworkConfig()
    }
    private fun initNetworkConfig(){
        val aGsonBuilder = GsonBuilder()
            .setLenient()
            .create()
        mBuilder = Retrofit.Builder().baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create(aGsonBuilder))
            .build()
    }
    fun <S> getApiService(service: Class<S>): S{
        return mBuilder.create(service)
    }
}