package com.saurav.cinemax.core.network

import com.saurav.cinemax.data.remote.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieApi : MovieApi = retrofit.create(MovieApi::class.java)
}