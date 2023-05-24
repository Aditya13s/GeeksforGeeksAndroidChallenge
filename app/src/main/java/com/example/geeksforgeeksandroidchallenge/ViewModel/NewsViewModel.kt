package com.example.geeksforgeeksandroidchallenge.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geeksforgeeksandroidchallenge.Data.Article
import com.example.geeksforgeeksandroidchallenge.Network.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _newsList = MutableLiveData<List<Article>?>()
    val newsList: MutableLiveData<List<Article>?> = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = NewsService.newsInstance.getNews()
                if (response.isSuccessful) {
                    val news = response.body()?.items
                    _newsList.value = news
                } else {
                    Log.i("newsResponse", "Filed to Load the News")
                }
            } catch (e: Exception) {
                Log.i("exception", e.message.toString())
            }
        }
    }
}