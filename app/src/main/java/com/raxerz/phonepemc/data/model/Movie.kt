package com.raxerz.phonepemc.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("results")
    val results: List<Result>
)

data class Result(
    @SerializedName("title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAvg: String
)