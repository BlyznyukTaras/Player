package com.litekreu.player

import android.content.Context
import android.media.MediaPlayer

class Player(private val context: Context) {
    var player: MediaPlayer? = null
    fun play() {
        MediaPlayer.create(context, R.raw.language).apply {
            player = this
            start()
        }
    }
    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}