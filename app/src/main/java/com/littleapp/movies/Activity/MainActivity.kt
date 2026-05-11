package com.littleapp.movies.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.littleapp.movies.R
import com.littleapp.movies.Unit.DATA
import com.littleapp.movies.Unit.THEME
import com.littleapp.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    var context = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DATA.MAIN = this
        navController = Navigation.findNavController(context, R.id.nav_host)
    }
}