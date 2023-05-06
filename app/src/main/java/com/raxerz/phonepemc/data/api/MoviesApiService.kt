package com.raxerz.phonepemc.data.api

import com.raxerz.phonepemc.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiService {

    @GET("movie/now_playing?api_key=38a73d59546aa378980a88b645f487fc&language=en-US&page=1")
    suspend fun getMovies(): Response<Movie>

}