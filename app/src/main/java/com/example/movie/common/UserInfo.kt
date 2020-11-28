package com.example.movie.common

data class UserInfo (
        var avatar:Avatar,
        var status_code:Int,
        var success:Boolean,
        var status_message:String,
        var username:String,
        var id:Int,
)
data class Avatar(
        var tmdb:Tmdb
)
data class Tmdb(
        var avatar_path:String
)