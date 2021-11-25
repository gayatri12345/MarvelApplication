package com.sample.marvelapplication.presentation.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Custom Bindings
 */
@BindingAdapter("image")
fun loadImage(imageView: ImageView, imageURL: String?) {
    Glide.with(imageView.context).load(imageURL)
        .centerCrop().into(imageView)
}
