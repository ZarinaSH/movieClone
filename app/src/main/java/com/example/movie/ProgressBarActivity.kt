package com.example.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.progress_bar_activity.*

class ProgressBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_bar_activity)
        txtView.setOnClickListener {
            progressBarContainer.isVisible = true
        }
        progressBarContainer.setOnClickListener {
            progressBarContainer.isVisible = false
        }
    }
}