package com.raxerz.phonepemc.usecase

import android.util.Log
import com.raxerz.phonepemc.data.model.Movie
import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.phonepemc.data.repository.MoviesRepository

typealias SavePlaylistUseCase = UseCase<PlaylistInfo, Unit>

class SavePlaylist constructor(private val moviesRepository: MoviesRepository): SavePlaylistUseCase {

    override suspend fun perform(params: PlaylistInfo): Unit {
        moviesRepository.savePlaylist(params)
    }


}