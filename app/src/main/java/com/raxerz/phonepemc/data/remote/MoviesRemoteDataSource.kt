package com.raxerz.phonepemc.data.remote

import android.util.Log
import com.raxerz.phonepemc.data.api.MoviesApiService
import com.raxerz.phonepemc.data.model.Movie

class MoviesRemoteDataSource constructor(private val moviesApiService: MoviesApiService) {

    suspend fun getAllMovies(): ResultResponse<Movie> {
        return runCatching {
            Log.d("WordsListRemoteDataSource", "getStartingWordsList")
            val response = moviesApiService.getMovies()

            if (response.isSuccessful) {
                response.body()?.let {
                    ResultResponse.success(it)
                } ?: ResultResponse.error("Result is empty!")
            } else {
                ResultResponse.error(response.message())
            }
        }.getOrDefault(ResultResponse.error("Something went wrong!"))
    }

}