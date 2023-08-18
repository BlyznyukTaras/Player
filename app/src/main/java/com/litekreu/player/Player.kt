package com.litekreu.player

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

class Player(private val context: Context, @RawRes val songsList: List<Int>, var id: Int) {
    var player: MediaPlayer? = null
    fun play() {
        if (player != null && !player!!.isPlaying) player!!.start()
        else {
            MediaPlayer.create(context, songsList[index]).apply {
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