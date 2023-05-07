package com.raxerz.phonepemc.usecase

import android.util.Log
import com.raxerz.phonepemc.data.model.Movie
import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.phonepemc.data.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

typealias FetchPlaylistUseCase = UseCase<Unit, Flow<List<PlaylistInfo>>>

class FetchPlaylist constructor(private val moviesRepository: MoviesRepository): FetchPlaylistUseCase {

    override suspend fun perform(params: Unit): Flow<List<PlaylistInfo>> {
        return moviesRepository.getPlaylists()
    }


}