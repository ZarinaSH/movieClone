package com.example.movie.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movie.R
import com.example.movie.second.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonInput.setOnClickListener() {
            var intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}
