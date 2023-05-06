package com.raxerz.wordsearch.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raxerz.phonepemc.data.model.PlaylistInfo

@Database(entities = [PlaylistInfo::class], version = 1, exportSchema = false)
abstract class PlaylistDatabase: RoomDatabase() {
    abstract fun getPlaylistDao(): PlaylistDao
}