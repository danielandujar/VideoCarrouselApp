package com.cry.videocarrousel.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer

class MainViewModel(val app: Application) : AndroidViewModel(app) {
    companion object {
        val TAG = MainViewModel.javaClass.simpleName
    }

    var exoPlayer : ExoPlayer = SimpleExoPlayer.Builder(app).build()

    init {

    }
}