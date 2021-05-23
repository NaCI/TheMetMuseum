package com.naci.sample.themetmuseum.common

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ImageLoader @Inject constructor(private val activity: AppCompatActivity) {

    private val requestOptions = RequestOptions.circleCropTransform()

    fun loadImage(imgUrl: String, target: ImageView) {
        Glide.with(activity).load(imgUrl).apply(requestOptions).into(target)
    }
}