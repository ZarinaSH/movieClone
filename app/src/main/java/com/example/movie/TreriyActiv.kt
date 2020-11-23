package com.example.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.treriy.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TreriyActiv : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.treriy)
        tre.text = SesionId.id

    }

}

