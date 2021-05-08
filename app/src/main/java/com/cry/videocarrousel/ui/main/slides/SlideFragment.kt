package com.cry.videocarrousel.ui.main.slides

import androidx.fragment.app.Fragment
import com.cry.videocarrousel.model.MediaItem
import com.cry.videocarrousel.model.MediaItemType

open class SlideFragment : Fragment() {
    companion object {
        fun createInstance(mediaItem: MediaItem) : SlideFragment{
            lateinit var instance : SlideFragment
            when (mediaItem.type){
                MediaItemType.IMAGE -> instance = ImageSlideFragment.newInstance(mediaItem.path)
                MediaItemType.VIDEO -> instance = VideoSlideFragment.newInstance(mediaItem.path)
            }
            return instance
        }
    }
}