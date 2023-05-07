package com.raxerz.phonepemc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raxerz.phonepemc.data.model.PlaylistInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaylistBottomSheet(
    modalSheetState: ModalBottomSheetState,
    playlistData: List<PlaylistInfo>,
    isFilter: Boolean,
    onPlaylistSelected: (playlist: String) -> Unit,
    onAddToNewPlaylistSelected: () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            Column{
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val list = playlistData.distinctBy {
                        it.playlistName
                    }
                    // can be uncommented when playlist info added
                    items(list) {
                        ShowPlayLists(playlistName = it.playlistName) {
                            onPlaylistSelected(it)
                        }
                    }
                    if(isFilter.not()) {
                        item {
                            Text(
                                modifier = Modifier.clickable {
                                    onAddToNewPlaylistSelected()
                                },
                                text = "+ Add a playlist"
                            )
                        }
                    }
                }
            }
        }
    ) {

    }
}