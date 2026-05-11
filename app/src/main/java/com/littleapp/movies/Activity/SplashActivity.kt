package com.littleapp.movies.Activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.littleapp.movies.CLASS
import com.littleapp.movies.Unit.THEME
import com.littleapp.movies.Unit.VOID
import com.littleapp.movies.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null
    var context: Context = this@SplashActivity

    var time_per_second = 2
    var time_final = time_per_millis * time_per_second

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        Handler(Looper.getMainLooper()).postDelayed({ launch() }, time_final.toLong())
    }

    private fun launch() {
        VOID.Intent1(context, CLASS.MAIN)
        finish()
    }

    companion object {
        const val time_per_millis = 1000
    }
}