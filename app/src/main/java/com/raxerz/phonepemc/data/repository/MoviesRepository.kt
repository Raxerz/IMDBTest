package com.raxerz.phonepemc.data.repository

import com.raxerz.phonepemc.data.local.MovieLocalDataSource
import com.raxerz.phonepemc.data.model.Movie
import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.phonepemc.data.remote.MoviesRemoteDataSource
import com.raxerz.phonepemc.data.remote.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepository constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource, private val movieLocalDataSource: MovieLocalDataSource) {

    fun getMovies(): Flow<ResultResponse<Movie>> {
        return flow {
            when(val apiResultResponse = moviesRemoteDataSource.getAllMovies()) {
                is ResultResponse.Success -> {
                    emit(ResultResponse.success(apiResultResponse.data))
                }
                is ResultResponse.Error -> {
                    emit(ResultResponse.error(apiResultResponse.message))
                }
            }
        }
    }

    fun getPlaylists(): Flow<List<PlaylistInfo>> {
        return flow {
            emit(movieLocalDataSource.fetchPlaylists())
        }
    }

    suspend fun savePlaylist(playlistInfo: PlaylistInfo) {
        movieLocalDataSource.saveMovieToPlaylist(playlistInfo)
    }

}