package com.example.geeksforgeeksandroidchallenge.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geeksforgeeksandroidchallenge.Data.Article
import com.example.geeksforgeeksandroidchallenge.Network.Api
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }
class ApiViewModel: ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _thumbnail = MutableLiveData<List<Article>>()
    val thumbnail: LiveData<List<Article>> = _thumbnail

    private val _title = MutableLiveData<List<Article>>()
    val title: LiveData<List<Article>> = _title

    private val _pubdate = MutableLiveData<List<Article>>()
    val pubdate: LiveData<List<Article>> = _pubdate

    init {
        getArticle()
    }

    private fun getArticle() {

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _thumbnail.value = Api.retrofitService.getThumbnail()
                _title.value = Api.retrofitService.getTitle()
                _pubdate.value = Api.retrofitService.getPubdate()
                Log.d("Success", thumbnail.value.toString())
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _thumbnail.value = listOf()
            }
        }
    }
}