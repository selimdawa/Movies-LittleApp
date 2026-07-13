package com.littleapp.movies.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import coil.load
import com.littleapp.movies.R

fun Context.openActivity(activityClass: Class<out Activity>, finish: Boolean = false) {
    val intent = Intent(this, activityClass)
    startActivity(intent)
    if (finish && this is Activity) {
        this.finish()
    }
}

fun ImageView.loadImage(url: String) {
    this.load(url) {
        crossfade(true)
        placeholder(R.color.image_profile)
    }
}