package com.raxerz.phonepemc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raxerz.phonepemc.R
import com.raxerz.phonepemc.data.model.Movie
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieList(movie: Movie) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { /*TODO*/ }
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.ic_filter),
//                    contentDescription = ""
//                )
//            }
//        },
//        content = {}
//    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movie.results) {
            MovieItem(it) {
                coroutineScope.launch {
                    if (modalSheetState.isVisible)
                        modalSheetState.hide()
                    else
                        modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            Column{
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(4) {

                    }
                }
            }
        }
    ) {

    }
}

@Composable
fun ShowPlayLists() {
    Text(text = "hello")
}