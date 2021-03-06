package com.example.movie.common

import com.example.movie.movieList.Movie
import retrofit2.Call
import retrofit2.http.*

const val API_KEY = "cf3f93c770820648b653a9e763863cb3"

interface AuthorizedApi {

    @GET("authentication/token/new")
    fun createToken(@Query("api_key") api_key: String = API_KEY): Call<ResponseCreateToken>

    @POST("authentication/token/validate_with_login")
    fun postLiP(@Query("api_key") api_key: String, @Body user: User): Call<ResponseCreateToken>

    @POST("authentication/session/new")
    fun sesionID(@Query("api_key") api_key: String, @Body token: Token): Call<LastPostData>

    @GET("account")
    fun userInfo(@Query("api_key") api_key: String,@Query("session_id")session_id:String):Call<UserInfo>

    @GET("movie/top_rated")
    fun getList(@Query("api_key") api_key: String):Call<Movie>

}
