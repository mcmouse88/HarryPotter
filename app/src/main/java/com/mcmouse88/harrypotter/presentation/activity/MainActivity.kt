package com.mcmouse88.harrypotter.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mcmouse88.harrypotter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}