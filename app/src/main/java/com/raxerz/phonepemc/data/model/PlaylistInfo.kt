package com.raxerz.phonepemc.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raxerz.phonepemc.utils.Constants

@Entity(tableName = Constants.LocalDB.PLAYLIST_INFO_TABLE)
data class PlaylistInfo(
    @PrimaryKey @ColumnInfo(name = "playlistName") var playlistName: String = "",
    @ColumnInfo(name = "title") var title: String? = "",
    @ColumnInfo(name = "posterPath") var posterPath: String? = "",
    @ColumnInfo(name = "rating") var rating: String? = ""
)