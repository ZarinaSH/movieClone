package com.example.movie.movieList

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.common.API_KEY
import com.example.movie.common.AuthorizedApi
import kotlinx.android.synthetic.main.activity_movie_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieListActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

    private val reqTokenClient = retrofit.create(AuthorizedApi::class.java)

    var films:MutableList<Result> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

//Response{protocol=h2, code=404, message=, url=https://api.themoviedb.org/movie/top_rated?api_key=cf3f93c770820648b653a9e763863cb3}

        reqTokenClient.getList(API_KEY).enqueue(object :Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.body()!=null){
                    films.addAll(response.body()!!.results)
                    if (films!=null){
                        rv.adapter = RvAdapterOne(films.toMutableList())
                    }


                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {

            }

        })





    }
}