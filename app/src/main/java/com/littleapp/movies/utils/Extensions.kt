package com.littleapp.movies.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

fun Context.openActivity(activityClass: Class<out Activity>, finish: Boolean = false) {
    val intent = Intent(this, activityClass)
    startActivity(intent)
    if (finish && this is Activity) {
        this.finish()
    }
}