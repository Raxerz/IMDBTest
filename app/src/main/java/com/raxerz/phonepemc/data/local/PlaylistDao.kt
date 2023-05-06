package com.raxerz.wordsearch.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.phonepemc.utils.Constants

@Dao
abstract class PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertMovie(playlistInfo: PlaylistInfo): Long

    @Query("SELECT * FROM ${Constants.LocalDB.PLAYLIST_INFO_TABLE}")
    abstract fun fetchPlaylist(): List<PlaylistInfo>
}