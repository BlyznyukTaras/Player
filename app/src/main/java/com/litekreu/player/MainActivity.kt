package com.litekreu.player

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun padModifier() = Modifier.fillMaxSize()

val songsPlaylist = listOf(R.raw.language)

@Composable
fun PlayerContainer(modifier: Modifier = Modifier, player: Player) {
    Box (
        modifier = modifier
            .padding(32.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
    ) {
        StockImage()
        Controls(plParam = player)
    }
}

@Composable
fun StockImage(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.stock),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .clip(RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun Controls(modifier: Modifier = padModifier(), plParam: Player) {
    Row(
        modifier = modifier
            .padding(bottom = 40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Transparent
            ),
            shape = CircleShape) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        OutlinedButton(
            onClick = { plParam.play() },
            border = BorderStroke(width = 2.dp, color = Color.White),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Home, 
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sample text", fontWeight = FontWeight.Light, fontSize = 20.sp)
        }
        Button(onClick = { /*TODO*/ }, colors =
            ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Transparent
            ),
            shape = CircleShape) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}