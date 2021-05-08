package com.cry.videocarrousel.ui.main.slides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cry.videocarrousel.R

class ImageSlideFragment : SlideFragment() {

    companion object {
        val TAG = ImageSlideFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(param1: String) =
            ImageSlideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PATH, imagePath)
                }
            }
    }

    private val ARG_PATH = "path"
    private var imagePath: String = ""
    private var imageView : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imagePath = it.getString(ARG_PATH, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_image_slide, container, false)
        imageView = root.findViewById(R.id.imageView)
        return root
    }

    override fun onResume() {
        super.onResume()

        Glide
            .with(this)
            .load(imagePath)
            .centerCrop()
            .into(imageView!!)
    }
}