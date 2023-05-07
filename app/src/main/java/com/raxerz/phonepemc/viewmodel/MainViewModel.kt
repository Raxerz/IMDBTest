package com.raxerz.phonepemc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raxerz.phonepemc.data.model.PlaylistInfo
import com.raxerz.phonepemc.usecase.FetchMovies
import com.raxerz.phonepemc.usecase.FetchPlaylist
import com.raxerz.phonepemc.usecase.SavePlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val fetchMovies: FetchMovies,
    private val savePlaylist: SavePlaylist,
    private val fetchPlaylist: FetchPlaylist
): ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    private val _playlistData = MutableLiveData<List<PlaylistInfo>>()
    val playlistData = _playlistData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchMovies.perform(Unit)
                .collect {movies ->
                    Log.d("RAJATH", movies.toString())
                    _state.value = ViewState.MoviesLoaded(movies)
                }
        }
    }

    fun savePlaylist(playlist: PlaylistInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            savePlaylist.perform(playlist)
        }
    }

    fun fetchPlaylist() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchPlaylist.perform(Unit)
                .collect {
                    _playlistData.postValue(it)
                }
        }
    }


}