package com.example.movie.common

import com.google.gson.annotations.SerializedName

data class ResponseCreateToken(
        var expires_at:String,
        @SerializedName("request_token")
        var requestToken:String,
        var success:Boolean
)