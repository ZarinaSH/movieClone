package com.example.movie


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import kotlinx.android.synthetic.main.activity_second.*
import androidx.core.widget.addTextChangedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SecondActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()

    private val reqTokenClient = retrofit.create(AuthorizedApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        reqTokenClient.createToken().enqueue(object : Callback<ResponseCreateToken> {
            override fun onResponse(call: Call<ResponseCreateToken>, response: Response<ResponseCreateToken>) {
                val token = response.body()?.requestToken
                if (token != null) {
                    validateWithLogin(User("WSRtest", "qwerty123", token))
                }
            }

            override fun onFailure(call: Call<ResponseCreateToken>, t: Throwable) {
                Log.e("SecondActivity", t.message ?: "error!")
            }
        })

        inputLogin.addTextChangedListener { editableText: Editable? ->
            if (editableText != null) {
                var login = editableText.toString()
            }
        }
        inputPassword.addTextChangedListener {
            if (it != null) {
                var pass = it.toString()
            }
        }

        buttonInput2.setOnClickListener {
            var intent = Intent(this, TreriyActiv::class.java)
            startActivity(intent)
        }

    }

    private fun validateWithLogin(userOne: User) {
        reqTokenClient.postLiP(API_KEY, userOne).enqueue(object : Callback<ResponseCreateToken> {
            override fun onResponse(call: Call<ResponseCreateToken>, response: Response<ResponseCreateToken>) {
                val body = response.body()
                if (body != null) {
                    createSessionId(body.requestToken)
                }
            }

            override fun onFailure(call: Call<ResponseCreateToken>, t: Throwable) {
                Log.e("SecondActivity", t.message ?: "error!")
            }
        })
    }

    private fun createSessionId(token: String) {
        val toc = Token(token)
        reqTokenClient.sesionID(API_KEY, toc).enqueue(object : Callback<LastPostData> {
            override fun onResponse(call: Call<LastPostData>, response: Response<LastPostData>) {
                if (response.body() != null)
                    SesionId.id = response.body()!!.session_id
                println()
            }

            override fun onFailure(call: Call<LastPostData>, t: Throwable) {
                test.text = "fail"
                Log.e("SecondActivity", t.message ?: "error!")
            }
        })
    }
}