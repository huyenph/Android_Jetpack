package com.utildev.androidjetpack.common.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

private val roundedCornerOption = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))
private val circleOption = RequestOptions().transforms(CenterCrop(), CircleCrop())

@BindingAdapter("url_roundedCorner")
fun loadImageUrlRoundedCorner(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .apply(roundedCornerOption)
        .into(imageView)
}

@BindingAdapter("url_circle")
fun loadImageUrlCircle(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .apply(circleOption)
        .into(imageView)
}

@BindingAdapter("url")
fun loadImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}