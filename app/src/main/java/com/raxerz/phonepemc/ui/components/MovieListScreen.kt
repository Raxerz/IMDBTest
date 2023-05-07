package com.raxerz.phonepemc.ui.components


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.raxerz.phonepemc.R
import com.raxerz.phonepemc.data.model.Movie
import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.phonepemc.data.model.Result
import com.raxerz.phonepemc.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListScreen(mainViewModel: MainViewModel, movie: Movie) {
    var refreshCount by remember { mutableStateOf(false) }
    var shouldFilter by remember { mutableStateOf(false) }
    var filterPlaylistName by remember { mutableStateOf<String?>(null) }
    val openDialog = remember { mutableStateOf(false) }
    var selectedMovie by remember { mutableStateOf<com.raxerz.phonepemc.data.model.Result?>(null) }
    val playlistData by mainViewModel.playlistData.observeAsState(listOf())
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    
    LaunchedEffect(key1 = refreshCount) {
        mainViewModel.fetchPlaylist()
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        shouldFilter = true
                        if (modalSheetState.isVisible) {
                            modalSheetState.hide()
                        }
                        else {
                            modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = ""
                )
            }
        },
        content = { _ ->
            val movieList = if(shouldFilter && filterPlaylistName != null) {
                playlistData.map {
                    Result(originalTitle = it.title ?: "", posterPath = it.posterPath ?: "", voteAvg = it.rating ?: "")
                }
            } else {
                movie.results
            }
            MovieList(
                movieList = movieList,
                playlistData = playlistData
            ) {
                selectedMovie = it
                coroutineScope.launch {
                    if (modalSheetState.isVisible)
                        modalSheetState.hide()
                    else
                        modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        }
    )
    PlaylistBottomSheet(
        modalSheetState = modalSheetState,
        playlistData = playlistData,
        isFilter = shouldFilter,
        onPlaylistSelected = {
            coroutineScope.launch {
                refreshCount = !refreshCount
                if(shouldFilter) {
                    if(filterPlaylistName.equals(it)) {
                        shouldFilter = false
                        filterPlaylistName = null
                    } else {
                        filterPlaylistName = it
                    }
                    modalSheetState.hide()
                } else {
                    filterPlaylistName = null
                    mainViewModel.savePlaylist(
                        PlaylistInfo(
                            playlistName = it,
                            posterPath = selectedMovie?.posterPath ?: "",
                            title = selectedMovie?.originalTitle ?: "",
                            rating = selectedMovie?.voteAvg
                        )
                    )
                }
            }
        },
        onAddToNewPlaylistSelected = {
            openDialog.value = true
        }
    )
    if(openDialog.value) {
        refreshCount = !refreshCount
        AddPlayList({
            coroutineScope.launch {
                mainViewModel.savePlaylist(
                    PlaylistInfo(
                        playlistName = it,
                        posterPath = selectedMovie?.posterPath ?: "",
                        title = selectedMovie?.originalTitle ?: "",
                        rating = selectedMovie?.voteAvg
                    )
                )
            }
        }) {
            openDialog.value = false
        }
    }
}
