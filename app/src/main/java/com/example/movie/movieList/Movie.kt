package com.example.movie.movieList

data class Movie(
    var results:List<Result>
)
data class Result(
    var original_title:String,
    var poster_path:String,
    var id:Int
)