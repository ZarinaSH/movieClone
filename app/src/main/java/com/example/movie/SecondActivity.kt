package com.example.movie


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_second.*
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

    var login:String=""
    var password:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        var a=""
        var b =""

        inputPassword.addTextChangedListener { editableText: Editable? ->

            if (editableText != null)
            {
                a = editableText.toString()
            }
        }
        inputLogin.addTextChangedListener { editableText: Editable? ->

            if (editableText != null)
            {
                b = editableText.toString()
            }
        }

        buttonInput2.setOnClickListener {
            password = a
            login=b
            oneeeee(login,password)

        }

    }
    private fun oneeeee(login:String,password:String){
        reqTokenClient.createToken().enqueue(object : Callback<ResponseCreateToken> {
            override fun onResponse(call: Call<ResponseCreateToken>, response: Response<ResponseCreateToken>) {
                val token = response.body()?.requestToken
                if (token != null) {
                    validateWithLogin(User("WSRtest", "qwerty123", token))
                    //validateWithLogin(User(login, password, token))
                }
            }
            override fun onFailure(call: Call<ResponseCreateToken>, t: Throwable) {
                Log.e("SecondActivity", t.message ?: "error!")
            }
        })
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
                    test.text=response.body()!!.session_id
                    SesionId.id = response.body()!!.session_id
                var intent = Intent(this@SecondActivity, TreriyActivity::class.java)
                 startActivity(intent)
                println()
            }


            override fun onFailure(call: Call<LastPostData>, t: Throwable) {
                test.text = "fail"
                Log.e("SecondActivity", t.message ?: "error!")
            }
        })
    }
}