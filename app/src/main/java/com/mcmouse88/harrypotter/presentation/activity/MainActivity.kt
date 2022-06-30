package com.mcmouse88.harrypotter.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mcmouse88.harrypotter.R
import com.mcmouse88.harrypotter.presentation.viewmodel.SplashViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}