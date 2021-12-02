package com.sample.marvelapplication.presentation.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.domain.model.MarvelCharacter
import com.sample.marvelapplication.presentation.characterlist.CharacterListAdapter

/**
 * Custom Bindings
 */
@BindingAdapter("image")
fun loadImage(imageView: ImageView, imageURL: String?) {
    Glide.with(imageView.context).load(imageURL)
        .centerCrop().into(imageView)
}

@BindingAdapter("listData")
fun loadListData(recyclerView: RecyclerView, data: List<MarvelCharacter>) {
    (recyclerView.adapter as CharacterListAdapter).addData(data)
    recyclerView.adapter?.notifyDataSetChanged()
}