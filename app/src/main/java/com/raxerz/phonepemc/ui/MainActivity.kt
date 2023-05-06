package com.raxerz.phonepemc.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raxerz.phonepemc.ui.components.MovieList
import com.raxerz.phonepemc.viewmodel.MainViewModel
import com.raxerz.phonepemc.viewmodel.ViewState
import com.raxerz.wordsearch.ui.theme.WordSearchTheme
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when(val state = mainViewModel.state.collectAsState(Dispatchers.Main).value) {
                        ViewState.Loading -> {

                        }
                        is ViewState.MoviesLoaded -> {
                            MovieList(state.movies)
                        }
                        is ViewState.Error -> {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    WordSearchTheme {
        Greeting("Android")
    }
}