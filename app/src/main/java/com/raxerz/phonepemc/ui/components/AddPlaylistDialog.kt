package com.raxerz.phonepemc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddPlayList(onPlaylistAdded:(text: String) -> Unit, onDismiss: () -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = "Enter a playlist name")
        },
        text = {
            TextField(value = text, onValueChange = {
                text = it
            })
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .weight(0.5f)
                        .background(color = Color.Transparent),
                    onClick = { onDismiss() }
                ) {
                    Text("CANCEL")
                }
                Button(
                    modifier = Modifier
                        .weight(0.5f)
                        .background(color = Color.Transparent),
                    onClick = {
                        onPlaylistAdded(text)
                        onDismiss()
                    }
                ) {
                    Text("OK")
                }
            }
        }
    )
}