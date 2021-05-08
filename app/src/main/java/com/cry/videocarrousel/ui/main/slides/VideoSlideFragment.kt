package com.cry.videocarrousel.ui.main.slides

import android.media.MediaMetadata
import android.media.MediaMetadataRetriever
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.cry.videocarrousel.R
import com.cry.videocarrousel.ui.main.MainViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class VideoSlideFragment : SlideFragment(), View.OnTouchListener {
    companion object {
        @JvmStatic
        fun newInstance(path: String) =
            VideoSlideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PATH, path)
                }
            }
    }

    private val ARG_PATH = "path"
    private var videoPath: String = ""
    private lateinit var playerView: PlayerView
    private lateinit var previewImageView : ImageView

    val viewModel : VideoSlideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoPath = it.getString(ARG_PATH, "")
        }
        val avm : MainViewModel by activityViewModels()
        viewModel.activityViewModel = avm
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_video_slide, container, false)
        playerView = root.findViewById(R.id.playerView)
        previewImageView = root.findViewById(R.id.previewImageView)
        root.setOnTouchListener(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPreview()
        loadMedia()
    }

    override fun onResume() {
        super.onResume()
        startVideo()
    }

    override fun onPause() {
        releasePlayer()
        super.onPause()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_DOWN -> {
                pauseVideo()
            }
            MotionEvent.ACTION_UP -> {
                startVideo()
            }
        }
        return false
    }

    private fun startVideo(){
        showHidePreview(false)
        viewModel.exoPlayer.playWhenReady = true
    }
    private fun pauseVideo(){
        viewModel.exoPlayer.playWhenReady = false
    }
    private fun releasePlayer(){
        viewModel.exoPlayer.release()
    }

    private fun loadMedia(){
        viewModel.exoPlayer.setMediaSource(
            ProgressiveMediaSource.Factory(DefaultDataSourceFactory(requireContext()))
            .createMediaSource(MediaItem.fromUri(videoPath)))

        viewModel.exoPlayer.prepare()
    }

    private fun loadPreview(){
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(videoPath)
        val preview = retriever.getFrameAtTime(0)
        preview?.let {
            previewImageView.setImageBitmap(preview)
            showHidePreview(true)
        }
    }

    private fun showHidePreview(show : Boolean){
        previewImageView.visibility = if (show) View.VISIBLE else View.GONE
    }

}