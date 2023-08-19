package com.litekreu.player

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

class Player(private val context: Context) {
    var player: MediaPlayer? = null
    fun play(@RawRes song: Int) {
        if (player != null && !player!!.isPlaying) player!!.start()
        else {
            MediaPlayer.create(context, song).apply {
                player = this
                start()
            }
        }
    }
    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
    fun pause() { player?.pause() }
    fun isPlaying(): Boolean = player?.isPlaying ?: false
}