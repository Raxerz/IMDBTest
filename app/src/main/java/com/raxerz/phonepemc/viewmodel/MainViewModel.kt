package com.raxerz.phonepemc.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raxerz.phonepemc.usecase.FetchMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val fetchMovies: FetchMovies
): ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    init {
        viewModelScope.launch {
            fetchMovies.perform(Unit)
                .collect {
                    Log.d("RAJATH", it.toString())
                    _state.value = ViewState.MoviesLoaded(it)
                }
        }
    }

}