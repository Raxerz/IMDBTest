package com.raxerz.phonepemc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.raxerz.phonepemc.R

@Composable
fun MovieItem(movie: com.raxerz.phonepemc.data.model.Result, onStarClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = LightGray
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/${movie.posterPath}"),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                contentScale = ContentScale.FillWidth
            )
            Row(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier.weight(8f)) {
                    Text(text = movie.originalTitle)
                    Text(text = "Rating - ${movie.voteAvg}")
                }
                Column(modifier = Modifier.weight(2f)) {
                    Image(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = "Andy Rubin",
                        modifier = Modifier.size(48.dp)
                            .clickable {
                                onStarClicked()
                            }
                    )
                }
            }
        }
    }
}