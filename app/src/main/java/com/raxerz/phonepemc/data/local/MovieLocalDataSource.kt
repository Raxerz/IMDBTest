package com.raxerz.phonepemc.data.local

import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.wordsearch.data.repository.local.PlaylistDao

class MovieLocalDataSource constructor(private val playlistDao: PlaylistDao) {

    suspend fun saveMovieToPlaylist(playlistInfo: PlaylistInfo): Long {
        return playlistDao.insertMovie(playlistInfo)
    }

    suspend fun fetchPlaylists(): List<PlaylistInfo> {
        return playlistDao.fetchPlaylist()
    }

}