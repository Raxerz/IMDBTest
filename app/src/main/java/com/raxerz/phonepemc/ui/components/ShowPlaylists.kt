package com.raxerz.phonepemc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShowPlayLists(playlistName: String, onPlaylistAdded: (text: String) -> Unit) {
    Text(
        text = playlistName,
        modifier = Modifier.clickable {
            onPlaylistAdded(playlistName)
        }
    )
}