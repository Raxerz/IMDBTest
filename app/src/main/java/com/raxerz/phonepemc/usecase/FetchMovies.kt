package com.raxerz.phonepemc.usecase

import android.util.Log
import com.raxerz.phonepemc.data.model.Movie
import com.raxerz.phonepemc.data.remote.ResultResponse
import com.raxerz.phonepemc.data.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias FetchMoviesUseCase = UseCase<Unit, Flow<Movie>>

class FetchMovies constructor(private val moviesRepository: MoviesRepository): FetchMoviesUseCase {

    override suspend fun perform(params: Unit): Flow<Movie> {
        return flow {
            moviesRepository.getMovies()
                .collect { result ->
                    when (result) {
                        is ResultResponse.Success -> {
                            emit(result.data)
                        }
                        is ResultResponse.Error -> {
                            Log.e("DownloadAllWordsUseCase", "Error occurred")
                        }
                    }

                }
        }
    }

}