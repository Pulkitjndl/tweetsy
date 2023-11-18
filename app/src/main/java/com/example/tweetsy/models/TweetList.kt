package com.example.tweetsy.models


import com.google.gson.annotations.SerializedName

data class TweetListItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("text")
    val text: String
)