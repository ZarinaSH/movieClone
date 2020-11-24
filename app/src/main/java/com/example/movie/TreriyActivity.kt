package com.example.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.treriy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TreriyActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()

    private val reqTokenClient = retrofit.create(AuthorizedApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.treriy)

        var idi :String = SesionId.id.toString()

        reqTokenClient.userInfo(API_KEY,idi).enqueue(object :Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {

                if (response.body()!=null){

                    username.text=response.body()!!.username
                    userId.text=response.body()!!.id.toString()
               }
                else username.text="null"
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
               username.text="fail"
            }

        })


    }

}

