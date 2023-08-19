package com.litekreu.player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.litekreu.player.ui.theme.PlayerTheme

class MainActivity : ComponentActivity() {
    val player by lazy {
        Player(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayerContainer(player = player)
                }
            }
        }
    }
}

val songsPlaylist = listOf(R.raw.pogo, R.raw.encore)

@Composable
fun PlayerContainer(modifier: Modifier = Modifier, player: Player) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
    ) {
        Controls(plParam = player)
    }
}

@Composable
fun Controls(modifier: Modifier = Modifier, plParam: Player) {
    var index by remember { mutableStateOf(0) }
    val mainOnClick = { if (!plParam.isPlaying()) plParam.play(songsPlaylist[index])
        else plParam.pause() }
    var hardCodedIsPlayingVar by remember { mutableStateOf(false) }
    var mainIcon by remember { mutableStateOf(Icons.Filled.PlayArrow) }
    mainIcon = when (hardCodedIsPlayingVar) {
        false -> Icons.Filled.PlayArrow
        else -> Icons.Filled.Refresh
    }
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(onClick = {
            plParam.stop()
            index--
            plParam.play(songsPlaylist[index])
            hardCodedIsPlayingVar = true
        },
            enabled = index != 0,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Transparent
            ),
            shape = CircleShape) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        IconButton(onClick = {
            hardCodedIsPlayingVar = !hardCodedIsPlayingVar
            mainOnClick.invoke()
        },
            modifier = Modifier.background(Color.White, shape = CircleShape)) {
            Icon(
                imageVector = mainIcon,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Button(onClick = {
            plParam.stop()
            index++
            plParam.play(songsPlaylist[index])
            hardCodedIsPlayingVar = true
        },
            enabled = index != songsPlaylist.size - 1,
            colors =
            ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Transparent),
            shape = CircleShape) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}