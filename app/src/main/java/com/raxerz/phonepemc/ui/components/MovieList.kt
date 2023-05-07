package com.raxerz.phonepemc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raxerz.phonepemc.data.model.Movie
import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.phonepemc.data.model.Result
import kotlinx.coroutines.launch

@Composable
fun MovieList(
    movieList: List<Result>,
    playlistData: List<PlaylistInfo>,
    onFavorited: (movieSelected: Result) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movieList) {
            val playlistName = playlistData.find {playlistInfo ->
                playlistInfo.title.equals(it.originalTitle)
            }?.playlistName ?: ""
            MovieItem(it, playlistName) {
                onFavorited(it)
            }
        }
    }
}