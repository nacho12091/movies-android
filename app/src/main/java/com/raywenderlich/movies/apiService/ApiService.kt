package com.raywenderlich.movies.apiService

import com.raywenderlich.movies.models.MoviesResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/3/movie/popular")
    fun getMostPopularMovies(@Query("api_key") apy_key: String?): Call<MoviesResponse>

}