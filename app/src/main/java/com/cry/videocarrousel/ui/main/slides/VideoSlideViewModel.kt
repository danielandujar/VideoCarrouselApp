package com.cry.videocarrousel.ui.main.slides

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cry.videocarrousel.ui.main.MainViewModel
import com.google.android.exoplayer2.ExoPlayer

class VideoSlideViewModel : ViewModel() {

    var activityViewModel : MainViewModel? = null

    val exoPlayer : ExoPlayer get() = activityViewModel?.exoPlayer!!

    private var mPreviewImageBitmap = MutableLiveData<Bitmap>()
    val previewImageBitmap : LiveData<Bitmap> get() = mPreviewImageBitmap

    private var mShowPreview = MutableLiveData<Boolean>()
    val showPreview : LiveData<Boolean> get() = mShowPreview

    fun setVideoPath() {

    }

    fun loadPreview(){
        val retriever = MediaMetadataRetriever()
    }

    fun playVideo() {}
    fun pauseVideo() {}
    fun stopVideo() {}

}