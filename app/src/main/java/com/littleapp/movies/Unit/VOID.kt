package com.littleapp.movies.Unit

import android.content.Context
import android.content.Intent

object VOID {
    fun Intent1(context: Context, c: Class<*>?) {
        val intent = Intent(context, c)
        context.startActivity(intent)
    }
}