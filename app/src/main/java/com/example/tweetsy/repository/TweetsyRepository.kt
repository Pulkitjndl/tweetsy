package com.example.tweetsy.repository

import android.util.Log
import com.example.tweetsy.api.TweetsyApi
import com.example.tweetsy.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsyRepository @Inject constructor(private val tweetsyApi:TweetsyApi){

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyApi.getCategories()
        try {
            if (response.isSuccessful && response.body() != null) {
                _categories.emit(response.body() ?: emptyList())
            }
        }catch (e:Exception){
            Log.d("Exception", "getCategories: $e")
        }
    }

    suspend fun getTweets(category: String) {
        val response = tweetsyApi.getTweetsy("tweets[?(@.category==\"$category\")]")
        try {
            if (response.isSuccessful && response.body() != null) {
                _tweets.emit(response.body() ?: emptyList())
            }
        }catch (e:Exception){
            Log.d("Exception", "getTweets: $e")
        }
    }
}