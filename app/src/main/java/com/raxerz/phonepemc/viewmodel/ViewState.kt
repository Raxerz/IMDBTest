package com.raxerz.phonepemc.viewmodel

import com.raxerz.phonepemc.data.model.Movie


sealed class ViewState {
    object Loading: ViewState()
    data class MoviesLoaded(val movies: Movie): ViewState()
    data class Error(val message: String): ViewState()
}